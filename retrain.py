"""
🎾 Padel Analytics — Réentraînement Automatique
================================================
1. Connexion MySQL (dw_padel_analytics)
2. Détection drift vs stats de référence
3. Si drift → réentraîne le modèle de classification
4. Sauvegarde modèle + rapport JSON
5. Log dans retraining.log

USAGE:
    python retrain.py          # Drift check + retrain si nécessaire
    python retrain.py --force  # Force le retrain
"""

import os, json, joblib, logging, argparse
import numpy as np
import pandas as pd
from datetime import datetime
from sqlalchemy import create_engine, text
from sklearn.model_selection import train_test_split, StratifiedKFold, GridSearchCV
from sklearn.linear_model import LogisticRegression
from sklearn.preprocessing import StandardScaler, LabelEncoder
from sklearn.impute import SimpleImputer
from sklearn.pipeline import Pipeline
from sklearn.metrics import roc_auc_score, accuracy_score, f1_score

# ── Config ────────────────────────────────────────────────────────────────────
DB_USER     = os.getenv("DB_USER",     "root")
DB_PASSWORD = os.getenv("DB_PASSWORD", "")
DB_HOST     = os.getenv("DB_HOST",     "host.docker.internal")
DB_PORT     = os.getenv("DB_PORT",     "3306")
DB_NAME     = os.getenv("DB_NAME",     "dw_padel_analytics")

MODELS_DIR  = os.path.join(os.path.dirname(os.path.abspath(__file__)), "models")
LOG_FILE    = os.path.join(os.path.dirname(os.path.abspath(__file__)), "retraining.log")
REPORT_FILE = os.path.join(MODELS_DIR, "last_retrain_report.json")
REF_FILE    = os.path.join(MODELS_DIR, "reference_stats.json")

DRIFT_THRESHOLD_POINTS  = 10.0
DRIFT_THRESHOLD_PLAYERS = 5.0

FEATURE_COLS = [
    'Pts_A','Pos_A','Move_A','Level_A',
    'Pts_B','Pos_B','Move_B','Level_B',
    'rank_diff','pts_diff',
    'Year','Month','Quarter','IsWeekend',
    'Gender','Match_type'
]

# ── Logging ───────────────────────────────────────────────────────────────────
os.makedirs(MODELS_DIR, exist_ok=True)
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s | %(levelname)s | %(message)s",
    handlers=[
        logging.FileHandler(LOG_FILE, encoding="utf-8"),
        logging.StreamHandler()
    ]
)
log = logging.getLogger(__name__)


def get_engine():
    url = f"mysql+mysqlconnector://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}"
    engine = create_engine(url, echo=False)
    with engine.connect() as conn:
        db = conn.execute(text("SELECT DATABASE()")).fetchone()[0]
    log.info(f"[DB] Connecté : {db}")
    return engine


def load_dataset(engine):
    log.info("[DATA] Chargement des tables...")
    fact_perf  = pd.read_sql("SELECT * FROM fact_performance", engine)
    dim_player = pd.read_sql("SELECT * FROM dim_player_ofc",   engine)
    dim_date   = pd.read_sql("SELECT * FROM dim_date",         engine)
    dim_gender = pd.read_sql("SELECT * FROM dim_gender",       engine)
    dim_type   = pd.read_sql("SELECT * FROM dim_type_match",   engine)

    df = fact_perf.copy()
    df = df.merge(dim_date[['DateKey','Year','Month','Quarter','IsWeekend']], on='DateKey', how='left')
    df = df.merge(dim_gender, on='Id_gender', how='left')
    df = df.merge(dim_type,   on='Id_type_match', how='left')

    ps = dim_player[['Id_Player','Points','Position','Move','Level']].copy()
    pA = ps.rename(columns={'Id_Player':'Id_Player_1_winner','Points':'Pts_A1','Position':'Pos_A1','Move':'Move_A1','Level':'Level_A1'})
    pB = ps.rename(columns={'Id_Player':'Id_Player_1_loser', 'Points':'Pts_B1','Position':'Pos_B1','Move':'Move_B1','Level':'Level_B1'})
    df = df.merge(pA[['Id_Player_1_winner','Pts_A1','Pos_A1','Move_A1','Level_A1']], on='Id_Player_1_winner', how='left')
    df = df.merge(pB[['Id_Player_1_loser', 'Pts_B1','Pos_B1','Move_B1','Level_B1']], on='Id_Player_1_loser',  how='left')

    np.random.seed(42)
    n = len(df)
    swap = np.random.rand(n) < 0.5
    rows = []
    for i, row in df.iterrows():
        s = swap[i % n]
        rows.append({
            'Pts_A':   row.get('Pts_B1' if s else 'Pts_A1',  np.nan),
            'Pos_A':   row.get('Pos_B1' if s else 'Pos_A1',  np.nan),
            'Move_A':  row.get('Move_B1' if s else 'Move_A1', np.nan),
            'Level_A': row.get('Level_B1' if s else 'Level_A1', np.nan),
            'Pts_B':   row.get('Pts_A1' if s else 'Pts_B1',  np.nan),
            'Pos_B':   row.get('Pos_A1' if s else 'Pos_B1',  np.nan),
            'Move_B':  row.get('Move_A1' if s else 'Move_B1', np.nan),
            'Level_B': row.get('Level_A1' if s else 'Level_B1', np.nan),
            'Year':    row.get('Year', np.nan),    'Month':    row.get('Month', np.nan),
            'Quarter': row.get('Quarter', np.nan), 'IsWeekend': row.get('IsWeekend', np.nan),
            'Gender':  row.get('Gender', np.nan),  'Match_type': row.get('Match_type', np.nan),
            'target':  0 if s else 1
        })

    ml_df = pd.DataFrame(rows)
    ml_df['rank_diff'] = ml_df['Pos_B'] - ml_df['Pos_A']
    ml_df['pts_diff']  = ml_df['Pts_A'] - ml_df['Pts_B']

    log.info(f"[DATA] Dataset: {ml_df.shape} | target: {ml_df['target'].value_counts().to_dict()}")

    current_stats = {
        "points_mean":  float(dim_player['Points'].mean()),
        "points_std":   float(dim_player['Points'].std()),
        "points_min":   float(dim_player['Points'].min()),
        "points_max":   float(dim_player['Points'].max()),
        "n_players":    int(len(dim_player)),
        "n_matches":    int(len(fact_perf))
    }
    return ml_df, current_stats


def detect_drift(current_stats):
    if not os.path.exists(REF_FILE):
        log.warning("[DRIFT] Pas de référence — création de la référence initiale")
        with open(REF_FILE, "w") as f:
            json.dump(current_stats, f, indent=2)
        return False, {"reason": "Première exécution — référence créée"}

    with open(REF_FILE) as f:
        ref = json.load(f)

    drift_pts    = abs(current_stats["points_mean"] - ref["points_mean"]) / (ref["points_mean"] + 1e-9) * 100
    drift_player = abs(current_stats["n_players"]   - ref["n_players"])   / (ref["n_players"]   + 1e-9) * 100

    report = {
        "points_drift_pct":  round(drift_pts, 2),
        "player_drift_pct":  round(drift_player, 2),
        "current_mean":      round(current_stats["points_mean"], 2),
        "reference_mean":    round(ref["points_mean"], 2),
        "current_players":   current_stats["n_players"],
        "reference_players": ref["n_players"]
    }
    log.info(f"[DRIFT] Points drift: {drift_pts:.1f}% | Joueurs drift: {drift_player:.1f}%")

    detected = drift_pts > DRIFT_THRESHOLD_POINTS or drift_player > DRIFT_THRESHOLD_PLAYERS
    if detected:
        log.warning("[DRIFT] ⚠️ DRIFT DÉTECTÉ → réentraînement déclenché")
    else:
        log.info("[DRIFT] ✅ Pas de drift significatif")
    return detected, report


def retrain(ml_df):
    log.info("[RETRAIN] Début réentraînement...")
    X = ml_df[FEATURE_COLS].copy()
    y = ml_df['target']

    for col in X.select_dtypes(include='object').columns:
        X[col] = LabelEncoder().fit_transform(X[col].astype(str))
    X = pd.DataFrame(SimpleImputer(strategy='median').fit_transform(X), columns=X.columns)

    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42, stratify=y)

    pipe = Pipeline([('scaler', StandardScaler()), ('lr', LogisticRegression(max_iter=1000, random_state=42))])
    grid = GridSearchCV(pipe, {'lr__C':[0.01,0.1,1,10],'lr__penalty':['l2'],'lr__solver':['lbfgs','liblinear']},
                        cv=StratifiedKFold(5, shuffle=True, random_state=42), scoring='roc_auc', n_jobs=-1)
    grid.fit(X_train, y_train)
    model = grid.best_estimator_

    y_pred  = model.predict(X_test)
    y_proba = model.predict_proba(X_test)[:, 1]
    auc = roc_auc_score(y_test, y_proba)
    acc = accuracy_score(y_test, y_pred)
    f1  = f1_score(y_test, y_pred)
    log.info(f"[RETRAIN] AUC={auc:.4f} | Acc={acc:.4f} | F1={f1:.4f}")

    ts = datetime.now().strftime("%Y%m%d_%H%M")
    joblib.dump(model, os.path.join(MODELS_DIR, f"classification_model_{ts}.pkl"))
    joblib.dump(model, os.path.join(MODELS_DIR, "classification_model.pkl"))
    joblib.dump(FEATURE_COLS, os.path.join(MODELS_DIR, "classification_features.pkl"))
    log.info(f"[RETRAIN] ✅ Modèle sauvegardé (classification_model_{ts}.pkl)")

    return auc, acc, f1, grid.best_params_


def save_report(retrained, reason, drift_report, auc=None, acc=None, f1=None, params=None):
    report = {"timestamp": datetime.now().isoformat(), "retrain": retrained,
              "reason": reason, "drift_report": drift_report}
    if retrained and auc is not None:
        report.update({"roc_auc": round(auc,4), "accuracy": round(acc,4),
                       "f1_score": round(f1,4), "best_params": params})
    with open(REPORT_FILE, "w") as f:
        json.dump(report, f, indent=2)
    log.info(f"[REPORT] Rapport sauvegardé")


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--force", action="store_true", help="Forcer le réentraînement")
    args = parser.parse_args()

    log.info("=" * 55)
    log.info("🎾 PADEL ANALYTICS — RÉENTRAÎNEMENT AUTOMATIQUE")
    log.info("=" * 55)

    try:
        engine = get_engine()
    except Exception as e:
        log.error(f"[MAIN] Connexion MySQL échouée : {e}")
        log.error("[MAIN] Vérifiez que phpMyAdmin/MySQL tourne sur localhost:3306")
        save_report(False, f"DB connection failed: {e}", {})
        return

    try:
        ml_df, current_stats = load_dataset(engine)
    except Exception as e:
        log.error(f"[MAIN] Erreur chargement : {e}")
        save_report(False, f"Data loading failed: {e}", {})
        return

    drift_detected, drift_report = detect_drift(current_stats)

    if drift_detected or args.force:
        reason = "Drift détecté" if drift_detected else "Forcé (--force)"
        try:
            auc, acc, f1, params = retrain(ml_df)
            with open(REF_FILE, "w") as f:
                json.dump(current_stats, f, indent=2)
            save_report(True, reason, drift_report, auc, acc, f1, params)
            log.info(f"✅ RÉENTRAÎNEMENT TERMINÉ — AUC: {auc:.4f}")
        except Exception as e:
            log.error(f"[MAIN] Erreur réentraînement : {e}")
            save_report(False, f"Retrain failed: {e}", drift_report)
    else:
        log.info("✅ Pas de réentraînement nécessaire")
        save_report(False, "No drift detected", drift_report)

    log.info("🏁 Terminé.")


if __name__ == "__main__":
    main()
