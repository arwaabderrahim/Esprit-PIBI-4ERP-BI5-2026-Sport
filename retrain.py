"""
🎾 Padel Analytics — Retraining Automatique avec Data Drift Detection
Script déclenché par n8n (Cron hebdomadaire ou détection de drift)
"""
import mlflow
import mlflow.sklearn
import sys
import io
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')
sys.stderr = io.TextIOWrapper(sys.stderr.buffer, encoding='utf-8')
import joblib
import pandas as pd
import numpy as np
import os
import json
import logging
import random
from datetime import datetime
from sqlalchemy import create_engine
from sklearn.linear_model import LogisticRegression
from sklearn.preprocessing import StandardScaler
from sklearn.impute import SimpleImputer
from sklearn.pipeline import Pipeline
from sklearn.model_selection import train_test_split
from sklearn.metrics import roc_auc_score

# ─────────────────────────────────────────────
# Configuration logs
# ─────────────────────────────────────────────
logging.basicConfig(
    filename="retraining.log",
    level=logging.INFO,
    format="%(asctime)s | %(levelname)s | %(message)s"
)

print("🔄 Démarrage du retraining pipeline...")
logging.info("=== RETRAINING PIPELINE DÉMARRÉ ===")

# ─────────────────────────────────────────────
# 1. Connexion MySQL
# ─────────────────────────────────────────────
try:
    engine = create_engine(
        'mysql+mysqlconnector://root:@127.0.0.1:3306/dw_padel_analytics'
    )
    print("✅ Connexion MySQL OK")
    logging.info("Connexion MySQL réussie")
except Exception as e:
    print(f"❌ Erreur connexion MySQL : {e}")
    logging.error(f"Erreur connexion MySQL : {e}")
    exit(1)

# ─────────────────────────────────────────────
# 2. Chargement des nouvelles données
# ─────────────────────────────────────────────
try:
    dim_player = pd.read_sql('SELECT * FROM dim_player_ofc', engine)
    fact_perf  = pd.read_sql('SELECT * FROM fact_performance', engine)
    print(f"✅ Données chargées : {len(fact_perf)} matchs | {len(dim_player)} joueurs")
    logging.info(f"Données chargées : {len(fact_perf)} lignes")
except Exception as e:
    print(f"❌ Erreur chargement données : {e}")
    logging.error(f"Erreur chargement données : {e}")
    exit(1)

# ─────────────────────────────────────────────
# 3. DÉTECTION DU DATA DRIFT
# ─────────────────────────────────────────────
print("\n🔍 Détection du data drift...")

DRIFT_DETECTED = False
drift_report   = {}
current_stats  = {}

try:
    ref_stats_path = "models/reference_stats.json"

    # Statistiques actuelles
    current_stats = {
        "points_mean": float(dim_player['Points'].mean()),
        "points_std":  float(dim_player['Points'].std()),
        "points_min":  float(dim_player['Points'].min()),
        "points_max":  float(dim_player['Points'].max()),
        "n_players":   int(len(dim_player)),
        "n_matches":   int(len(fact_perf))
    }

    if os.path.exists(ref_stats_path):
        with open(ref_stats_path, "r") as f:
            ref_stats = json.load(f)

        # Drift sur moyenne des points (seuil 10%)
        drift_mean = abs(current_stats["points_mean"] - ref_stats["points_mean"])
        drift_pct  = drift_mean / (ref_stats["points_mean"] + 1e-9) * 100

        # Drift sur nombre de joueurs (seuil 5%)
        player_diff = abs(current_stats["n_players"] - ref_stats["n_players"])
        player_pct  = player_diff / (ref_stats["n_players"] + 1e-9) * 100

        drift_report = {
            "points_drift_pct":  round(drift_pct, 2),
            "player_drift_pct":  round(player_pct, 2),
            "current_mean":      round(current_stats["points_mean"], 2),
            "reference_mean":    round(ref_stats["points_mean"], 2),
            "current_players":   current_stats["n_players"],
            "reference_players": ref_stats["n_players"]
        }

        if drift_pct > 10 or player_pct > 5:
            DRIFT_DETECTED = True
            print(f"⚠️  DRIFT DÉTECTÉ !")
            print(f"   Points drift : {drift_pct:.1f}% (seuil: 10%)")
            print(f"   Joueurs drift: {player_pct:.1f}% (seuil: 5%)")
            logging.warning(f"DRIFT DÉTECTÉ : points={drift_pct:.1f}% | joueurs={player_pct:.1f}%")
        else:
            print(f"✅ Pas de drift significatif")
            print(f"   Points drift : {drift_pct:.1f}%")
            print(f"   Joueurs drift: {player_pct:.1f}%")
            logging.info(f"Pas de drift : points={drift_pct:.1f}%")
    else:
        print("📌 Première exécution → entraînement initial")
        DRIFT_DETECTED = True
        drift_report = {"message": "Première exécution"}
        logging.info("Première exécution")

except Exception as e:
    print(f"⚠️  Erreur drift detection : {e} → entraînement forcé")
    logging.warning(f"Erreur drift : {e}")
    DRIFT_DETECTED = True

# ─────────────────────────────────────────────
# 4. DÉCISION : Réentraîner ou non ?
# ─────────────────────────────────────────────
FORCE_RETRAIN = "--force" in sys.argv

if not DRIFT_DETECTED and not FORCE_RETRAIN:
    print("\n✅ Pas de drift — Réentraînement non nécessaire")
    logging.info("Réentraînement ignoré — pas de drift")
    report = {
        "timestamp":    datetime.now().isoformat(),
        "retrain":      False,
        "reason":       "No drift detected",
        "drift_report": drift_report
    }
    with open("models/last_retrain_report.json", "w") as f:
        json.dump(report, f, indent=2)
    print(json.dumps(report, indent=2))
    exit(0)

# ─────────────────────────────────────────────
# 5. PRÉPARATION DES DONNÉES
# ─────────────────────────────────────────────
print("\n📊 Préparation des données...")

try:
    matches = []
    for _, row in fact_perf.iterrows():
        w1 = dim_player[dim_player['Id_Player'] == row['Id_Player_1_winner']]
        l1 = dim_player[dim_player['Id_Player'] == row['Id_Player_1_loser']]
        if w1.empty or l1.empty:
            continue
        Pts_W = float(w1['Points'].values[0])
        Pos_W = float(w1['Position'].values[0])
        Pts_L = float(l1['Points'].values[0])
        Pos_L = float(l1['Position'].values[0])
        if random.random() > 0.5:
            matches.append({'Pts_A': Pts_W, 'Pos_A': Pos_W, 'Pts_B': Pts_L,
                            'Pos_B': Pos_L, 'pts_diff': Pts_W-Pts_L,
                            'rank_diff': Pos_W-Pos_L, 'target': 1})
        else:
            matches.append({'Pts_A': Pts_L, 'Pos_A': Pos_L, 'Pts_B': Pts_W,
                            'Pos_B': Pos_W, 'pts_diff': Pts_L-Pts_W,
                            'rank_diff': Pos_L-Pos_W, 'target': 0})

    df_ml        = pd.DataFrame(matches).dropna()
    feature_cols = ['Pts_A', 'Pos_A', 'Pts_B', 'Pos_B', 'pts_diff', 'rank_diff']
    X = df_ml[feature_cols]
    y = df_ml['target']
    print(f"✅ Dataset : {len(df_ml)} exemples")
    logging.info(f"Dataset : {len(df_ml)} exemples")

except Exception as e:
    print(f"❌ Erreur préparation : {e}")
    logging.error(f"Erreur préparation : {e}")
    exit(1)

# ─────────────────────────────────────────────
# 6. ENTRAÎNEMENT
# ─────────────────────────────────────────────
# Étape 6 ENTRAÎNEMENT
try:
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.2, random_state=42, stratify=y
    )
    
    pipe = Pipeline([
        ('imputer', SimpleImputer(strategy='median')),
        ('scaler',  StandardScaler()),
        ('lr',      LogisticRegression(C=1.0, max_iter=1000, random_state=42))
    ])

    # MLflow DANS le try ✅
    mlflow.set_experiment("Padel-Analytics-Classification")
    
    with mlflow.start_run():
        pipe.fit(X_train, y_train)
        auc = roc_auc_score(y_test, pipe.predict_proba(X_test)[:, 1])
        
        mlflow.log_param("model", "LogisticRegression")
        mlflow.log_param("C", 1.0)
        mlflow.log_param("max_iter", 1000)
        mlflow.log_param("test_size", 0.2)
        mlflow.log_param("n_features", len(feature_cols))
        
        mlflow.log_metric("roc_auc", round(auc, 4))
        mlflow.log_metric("n_examples", len(df_ml))
        mlflow.log_metric("n_train", len(X_train))
        mlflow.log_metric("n_test", len(X_test))
        
        mlflow.sklearn.log_model(pipe, "classification_model")
        
        print(f"[OK] ROC-AUC : {auc:.4f}")
        logging.info(f"ROC-AUC : {auc:.4f}")

except Exception as e:
    print(f"Erreur entrainement : {e}")
    logging.error(f"Erreur entrainement : {e}")
    exit(1)
# ─────────────────────────────────────────────
# 7. SAUVEGARDE + VERSIONING
# ─────────────────────────────────────────────
print("\n💾 Sauvegarde...")

try:
    os.makedirs("models/versions", exist_ok=True)
    version       = datetime.now().strftime("%Y%m%d_%H%M")
    versioned_path = f"models/versions/classification_model_{version}.pkl"

    # Sauvegarde versionnée
    joblib.dump(pipe, versioned_path)

    # Remplace le modèle courant (utilisé par FastAPI)
    joblib.dump(pipe, "models/classification_model.pkl")
    joblib.dump(feature_cols, "models/classification_features.pkl")

    # Sauvegarde des nouvelles stats de référence
    with open("models/reference_stats.json", "w") as f:
        json.dump(current_stats, f, indent=2)

    # Rapport final
    report = {
        "timestamp":    datetime.now().isoformat(),
        "version":      version,
        "retrain":      True,
        "reason":       "Drift detected" if DRIFT_DETECTED else "Forced",
        "roc_auc":      round(auc, 4),
        "n_examples":   len(df_ml),
        "drift_report": drift_report,
        "model_path":   versioned_path
    }
    with open("models/last_retrain_report.json", "w") as f:
        json.dump(report, f, indent=2)

    print(f"\n=== ✅ RETRAINING TERMINÉ ===")
    print(f"Version  : {version}")
    print(f"ROC-AUC  : {auc:.4f}")
    print(f"Drift    : {'Oui ⚠️' if DRIFT_DETECTED else 'Non ✅'}")
    print(f"Modèle   : {versioned_path}")
    logging.info(f"Sauvegardé : {version} | AUC={auc:.4f}")
    print(json.dumps(report, indent=2))

except Exception as e:
    print(f"❌ Erreur sauvegarde : {e}")
    logging.error(f"Erreur sauvegarde : {e}")
    exit(1)
