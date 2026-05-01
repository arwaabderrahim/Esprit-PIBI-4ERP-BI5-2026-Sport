from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import joblib
import numpy as np
import pandas as pd
from prophet.serialize import model_from_json
import json
import logging

# === Imports pour le prétraitement ===
from sklearn.preprocessing import LabelEncoder
from sklearn.impute import SimpleImputer

# ─────────────────────────────────────────────
# Configuration des logs
# ─────────────────────────────────────────────
logging.basicConfig(
    filename="predictions.log",
    level=logging.INFO,
    format="%(asctime)s | %(levelname)s | %(message)s"
)

app = FastAPI(
    title="🎾 Padel Analytics ML API",
    description="API de prédiction pour le projet Padel Analytics — Classification, Régression, Clustering, Time Series",
    version="1.0.0"
)

# ─────────────────────────────────────────────
# Chargement des modèles
# ─────────────────────────────────────────────
try:
    clf = joblib.load("models/classification_model.pkl")
    clf_features = joblib.load("models/classification_features.pkl")
    print("✅ Modèle Classification chargé")
except Exception as e:
    print(f"❌ Erreur Classification : {e}")
    clf = None

try:
    reg = joblib.load("models/regression_model.pkl")
    reg_features = joblib.load("models/regression_features.pkl")
    print("✅ Modèle Régression chargé")
except Exception as e:
    print(f"❌ Erreur Régression : {e}")
    reg = None

try:
    clu = joblib.load("models/clustering_model.pkl")
    scaler = joblib.load("models/clustering_scaler.pkl")
    print("✅ Modèle Clustering chargé")
except Exception as e:
    print(f"❌ Erreur Clustering : {e}")
    clu = None

try:
    ts_model = joblib.load("models/timeseries_model.pkl")
    print("✅ Modèle Time Series chargé")
except Exception as e:
    try:
        with open("models/timeseries_model.json", "r") as f:
            ts_model = model_from_json(json.load(f))
        print("✅ Modèle Time Series (Prophet JSON) chargé")
    except Exception as e2:
        print(f"❌ Erreur Time Series : {e2}")
        ts_model = None


# ─────────────────────────────────────────────
# Schémas Pydantic
# ─────────────────────────────────────────────

class ClassificationInput(BaseModel):
    Pts_A: float
    Pos_A: float
    Move_A: float
    Level_A: str
    
    Pts_B: float
    Pos_B: float
    Move_B: float
    Level_B: str
    
    rank_diff: float
    pts_diff: float
    
    Year: int
    Month: int
    Quarter: int
    IsWeekend: int
    
    Gender: str
    Match_type: str


class RegressionInput(BaseModel):
    Points__Winner: float
    Points__Loser: float
    Points_W1: float
    Position_W1: float
    points_diff: float
    nb_sets: float
    is_3sets: int
    Year: int
    Month: int
    Quarter: int
    IsWeekend: int
    Gender: str
    Match_type: str
    Level_W1: str


class ClusteringInput(BaseModel):
    Points: float
    Position: float
    Move: float           # ← Ajouté
    total_wins: float
    total_loss: float
    total_matchs: float   # ← Ajouté
    win_rate: float
    Level: str            # ← Changé en str (catégoriel)
    Genre: str            # ← Changé en str (catégoriel)


class TimeSeriesInput(BaseModel):
    periods: int = 6


# ─────────────────────────────────────────────
# Routes
# ─────────────────────────────────────────────

@app.get("/")
def root():
    return {"message": "🎾 Padel Analytics ML API is running"}

@app.get("/health")
def health():
    return {
        "status": "OK",
        "models": {
            "classification": clf is not None,
            "regression": reg is not None,
            "clustering": clu is not None,
            "timeseries": ts_model is not None
        }
    }


# ====================== CLASSIFICATION ======================
@app.post("/predict/classification")
def predict_classification(data: ClassificationInput):
    if clf is None:
        raise HTTPException(status_code=503, detail="Modèle classification non disponible")
    
    try:
        X = pd.DataFrame([data.dict()])

        # Encodage (comme dans ton notebook)
        for col in ['Level_A', 'Level_B', 'Gender', 'Match_type']:
            X[col] = X[col].astype(str)
            le = LabelEncoder()
            X[col] = le.fit_transform(X[col])

        imputer = SimpleImputer(strategy='median')
        X = pd.DataFrame(imputer.fit_transform(X), columns=X.columns)

        X = X[clf_features]

        prediction = int(clf.predict(X)[0])
        proba = float(clf.predict_proba(X)[0][1])

        return {
            "prediction": prediction,
            "label": "Win ✅" if prediction == 1 else "Loss ❌",
            "win_probability": round(proba, 4),
            "model": "Logistic Regression"
        }
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Erreur de prédiction: {str(e)}")


# ====================== REGRESSION ======================
@app.post("/predict/regression")
def predict_regression(data: RegressionInput):
    if reg is None:
        raise HTTPException(status_code=503, detail="Modèle régression non disponible")
    
    try:
        X = pd.DataFrame([data.dict()])

        # Encodage des variables catégorielles
        for col in ['Gender', 'Match_type', 'Level_W1']:
            if col in X.columns:
                X[col] = X[col].astype(str)
                le = LabelEncoder()
                X[col] = le.fit_transform(X[col])

        imputer = SimpleImputer(strategy='median')
        X = pd.DataFrame(imputer.fit_transform(X), columns=X.columns)

        # Réordonner si tu as sauvegardé reg_features
        if 'reg_features' in globals() and reg_features is not None:
            X = X[reg_features]

        prediction = float(reg.predict(X)[0])

        return {
            "predicted_prize_money": round(prediction, 2),
            "currency": "EUR",
            "model": "Random Forest Regressor"
        }
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Erreur de prédiction: {str(e)}")


# ====================== CLUSTERING ======================# 
@app.post("/predict/clustering")
def predict_clustering(data: ClusteringInput):
    if clu is None:
        raise HTTPException(status_code=503, detail="Modèle clustering non disponible")
    
    try:
        # Convertir en DataFrame
        X = pd.DataFrame([data.dict()])

        # Encodage des variables catégorielles (comme dans ton notebook)
        for col in ['Level', 'Genre']:
            if col in X.columns:
                X[col] = X[col].astype(str)
                le = LabelEncoder()
                X[col] = le.fit_transform(X[col])

        # Imputation si nécessaire
        imputer = SimpleImputer(strategy='median')
        X = pd.DataFrame(imputer.fit_transform(X), columns=X.columns)

        # Réordonner exactement comme pendant l'entraînement
        # (Charge cluster_features si tu l'as sauvegardé)
        try:
            cluster_features = joblib.load("models/clustering_features.pkl")
            X = X[cluster_features]
        except:
            # Si tu n'as pas sauvegardé, on met l'ordre manuellement
            feature_order = ['Points', 'Position', 'Move', 'total_wins', 'total_loss',
                           'total_matchs', 'win_rate', 'Level', 'Genre']
            X = X[feature_order]

        # Scaling + Prédiction
        X_scaled = scaler.transform(X)
        cluster = int(clu.predict(X_scaled)[0])

        cluster_labels = {
            0: "Joueur amateur (faible classement)",
            1: "Joueur élite (top classement)",
            2: "Joueur intermédiaire"
        }

        return {
            "cluster": cluster,
            "profil": cluster_labels.get(cluster, f"Cluster {cluster}"),
            "model": "K-Means"
        }

    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Erreur de prédiction: {str(e)}")


# ====================== TIMESERIES ======================
@app.post("/predict/timeseries")
def predict_timeseries(data: TimeSeriesInput):
    if ts_model is None:
        raise HTTPException(status_code=503, detail="Modèle time series non disponible")
    
    try:
        future = ts_model.make_future_dataframe(periods=data.periods, freq="MS")
        forecast = ts_model.predict(future)
        last_n = forecast.tail(data.periods)[["ds", "yhat", "yhat_lower", "yhat_upper"]]

        return {
            "forecast": [
                {
                    "month": str(row["ds"])[:7],
                    "predicted": round(float(row["yhat"]), 1),
                    "lower": round(float(row["yhat_lower"]), 1),
                    "upper": round(float(row["yhat_upper"]), 1)
                }
                for _, row in last_n.iterrows()
            ],
            "model": "Prophet",
            "periods": data.periods
        }
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Erreur de prédiction: {str(e)}")

@app.post("/retrain")
def retrain_model():
    try:
        import subprocess
        result = subprocess.run(
            ["python", "retrain.py"],
            capture_output=True,
            text=True,
            encoding="utf-8",
            errors="replace",
            cwd="C:\\Users\\Asus\\Desktop\\n8n"
        )
        
        stdout = result.stdout if result.stdout else ""
        stderr = result.stderr if result.stderr else ""
        
        return {
            "status":      "success" if result.returncode == 0 else "error",
            "returncode":  result.returncode,
            "output":      stdout[-500:],
            "error":       stderr[-200:]
        }
    except Exception as e:
        logging.error(f"[RETRAIN] Erreur : {e}")
        raise HTTPException(status_code=500, detail=str(e))