from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import joblib
import numpy as np
import pandas as pd
from prophet.serialize import model_from_json
import json
import logging
import os
from datetime import datetime

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

# Logger aussi dans la console
console = logging.StreamHandler()
console.setLevel(logging.INFO)
logging.getLogger('').addHandler(console)

app = FastAPI(
    title="Padel Analytics ML API",
    description="API de prediction pour le projet Padel Analytics",
    version="1.0.0"
)
from fastapi.middleware.cors import CORSMiddleware

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
# ─────────────────────────────────────────────
# Chargement des modèles
# ─────────────────────────────────────────────
try:
    clf = joblib.load("models/classification_model.pkl")
    clf_features = joblib.load("models/classification_features.pkl")
    print("OK Modele Classification charge")
    logging.info("[STARTUP] Modele Classification charge avec succes")
except Exception as e:
    print(f"ERREUR Classification : {e}")
    logging.error(f"[STARTUP] Erreur Classification : {e}")
    clf = None

try:
    reg = joblib.load("models/regression_model.pkl")
    reg_features = joblib.load("models/regression_features.pkl")
    print("OK Modele Regression charge")
    logging.info("[STARTUP] Modele Regression charge avec succes")
except Exception as e:
    print(f"ERREUR Regression : {e}")
    logging.error(f"[STARTUP] Erreur Regression : {e}")
    reg = None

try:
    clu = joblib.load("models/clustering_model.pkl")
    scaler = joblib.load("models/clustering_scaler.pkl")
    print("OK Modele Clustering charge")
    logging.info("[STARTUP] Modele Clustering charge avec succes")
except Exception as e:
    print(f"ERREUR Clustering : {e}")
    logging.error(f"[STARTUP] Erreur Clustering : {e}")
    clu = None

try:
    ts_model = joblib.load("models/timeseries_model.pkl")
    print("OK Modele Time Series charge")
    logging.info("[STARTUP] Modele Time Series charge avec succes")
except Exception as e:
    try:
        with open("models/timeseries_model.json", "r") as f:
            ts_model = model_from_json(json.load(f))
        print("OK Modele Time Series JSON charge")
        logging.info("[STARTUP] Modele Time Series JSON charge avec succes")
    except Exception as e2:
        print(f"ERREUR Time Series : {e2}")
        logging.error(f"[STARTUP] Erreur Time Series : {e2}")
        ts_model = None


# ─────────────────────────────────────────────
# Schemas Pydantic
# ─────────────────────────────────────────────

class ClassificationInput(BaseModel):
    Pts_A:      float
    Pos_A:      float
    Move_A:     float
    Level_A:    str
    Pts_B:      float
    Pos_B:      float
    Move_B:     float
    Level_B:    str
    rank_diff:  float
    pts_diff:   float
    Year:       int
    Month:      int
    Quarter:    int
    IsWeekend:  int
    Gender:     str
    Match_type: str


class RegressionInput(BaseModel):
    Points__Winner: float
    Points__Loser:  float
    Points_W1:      float
    Position_W1:    float
    points_diff:    float
    nb_sets:        float
    is_3sets:       int
    Year:           int
    Month:          int
    Quarter:        int
    IsWeekend:      int
    Gender:         str
    Match_type:     str
    Level_W1:       str


class ClusteringInput(BaseModel):
    Points:       float
    Position:     float
    Move:         float
    total_wins:   float
    total_loss:   float
    total_matchs: float
    win_rate:     float
    Level:        str
    Genre:        str


class TimeSeriesInput(BaseModel):
    periods: int = 6


# ─────────────────────────────────────────────
# Routes
# ─────────────────────────────────────────────

@app.get("/")
def root():
    logging.info("[ROOT] Page d'accueil appelee")
    return {
        "message": "Padel Analytics ML API is running",
        "endpoints": [
            "/predict/classification",
            "/predict/regression",
            "/predict/clustering",
            "/predict/timeseries",
            "/retrain",
            "/health"
        ]
    }

@app.get("/health")
def health():
    status = {
        "status": "OK",
        "timestamp": datetime.now().isoformat(),
        "models": {
            "classification": clf is not None,
            "regression":     reg is not None,
            "clustering":     clu is not None,
            "timeseries":     ts_model is not None
        }
    }
    logging.info(f"[HEALTH] Status check : {status['models']}")
    return status


# ====================== CLASSIFICATION ======================
@app.post("/predict/classification")
def predict_classification(data: ClassificationInput):
    if clf is None:
        logging.error("[CLASSIFICATION] Modele non disponible")
        raise HTTPException(status_code=503, detail="Modele classification non disponible")

    try:
        X = pd.DataFrame([data.dict()])

        for col in ['Level_A', 'Level_B', 'Gender', 'Match_type']:
            X[col] = X[col].astype(str)
            le = LabelEncoder()
            X[col] = le.fit_transform(X[col])

        imputer = SimpleImputer(strategy='median')
        X = pd.DataFrame(imputer.fit_transform(X), columns=X.columns)
        X = X[clf_features]

        prediction = int(clf.predict(X)[0])
        proba      = float(clf.predict_proba(X)[0][1])
        label      = "Win" if prediction == 1 else "Loss"

        result = {
            "prediction":      prediction,
            "label":           f"{label}",
            "win_probability": round(proba, 4),
            "model":           "Logistic Regression"
        }

        logging.info(
            f"[CLASSIFICATION] "
            f"Pts_A={data.Pts_A} | Pts_B={data.Pts_B} | "
            f"pts_diff={data.pts_diff} | rank_diff={data.rank_diff} | "
            f"Prediction={label} | Probabilite={round(proba, 4)}"
        )

        return result

    except Exception as e:
        logging.error(f"[CLASSIFICATION] Erreur : {str(e)}")
        raise HTTPException(status_code=400, detail=f"Erreur de prediction: {str(e)}")


# ====================== REGRESSION ======================
@app.post("/predict/regression")
def predict_regression(data: RegressionInput):
    if reg is None:
        logging.error("[REGRESSION] Modele non disponible")
        raise HTTPException(status_code=503, detail="Modele regression non disponible")

    try:
        X = pd.DataFrame([data.dict()])

        for col in ['Gender', 'Match_type', 'Level_W1']:
            if col in X.columns:
                X[col] = X[col].astype(str)
                le = LabelEncoder()
                X[col] = le.fit_transform(X[col])

        imputer = SimpleImputer(strategy='median')
        X = pd.DataFrame(imputer.fit_transform(X), columns=X.columns)

        if 'reg_features' in globals() and reg_features is not None:
            X = X[reg_features]

        prediction = float(reg.predict(X)[0])

        result = {
            "predicted_prize_money": round(prediction, 2),
            "currency": "EUR",
            "model":    "Random Forest Regressor"
        }

        logging.info(
            f"[REGRESSION] "
            f"Winner_Points={data.Points__Winner} | "
            f"Loser_Points={data.Points__Loser} | "
            f"Prize_Money={round(prediction, 2)} EUR"
        )

        return result

    except Exception as e:
        logging.error(f"[REGRESSION] Erreur : {str(e)}")
        raise HTTPException(status_code=400, detail=f"Erreur de prediction: {str(e)}")


# ====================== CLUSTERING ======================
@app.post("/predict/clustering")
def predict_clustering(data: ClusteringInput):
    if clu is None:
        logging.error("[CLUSTERING] Modele non disponible")
        raise HTTPException(status_code=503, detail="Modele clustering non disponible")

    try:
        X = pd.DataFrame([data.dict()])

        for col in ['Level', 'Genre']:
            if col in X.columns:
                X[col] = X[col].astype(str)
                le = LabelEncoder()
                X[col] = le.fit_transform(X[col])

        imputer = SimpleImputer(strategy='median')
        X = pd.DataFrame(imputer.fit_transform(X), columns=X.columns)

        try:
            cluster_features = joblib.load("models/clustering_features.pkl")
            X = X[cluster_features]
        except:
            feature_order = ['Points', 'Position', 'Move', 'total_wins',
                           'total_loss', 'total_matchs', 'win_rate', 'Level', 'Genre']
            X = X[feature_order]

        X_scaled = scaler.transform(X)
        cluster   = int(clu.predict(X_scaled)[0])

        cluster_labels = {
            0: "Joueur amateur (faible classement)",
            1: "Joueur elite (top classement)",
            2: "Joueur intermediaire"
        }

        profil = cluster_labels.get(cluster, f"Cluster {cluster}")

        result = {
            "cluster": cluster,
            "profil":  profil,
            "model":   "K-Means"
        }

        logging.info(
            f"[CLUSTERING] "
            f"Points={data.Points} | Position={data.Position} | "
            f"win_rate={data.win_rate} | "
            f"Cluster={cluster} | Profil={profil}"
        )

        return result

    except Exception as e:
        logging.error(f"[CLUSTERING] Erreur : {str(e)}")
        raise HTTPException(status_code=400, detail=f"Erreur de prediction: {str(e)}")


# ====================== TIMESERIES ======================
@app.post("/predict/timeseries")
def predict_timeseries(data: TimeSeriesInput):
    if ts_model is None:
        logging.error("[TIMESERIES] Modele non disponible")
        raise HTTPException(status_code=503, detail="Modele time series non disponible")

    try:
        future   = ts_model.make_future_dataframe(periods=data.periods, freq="MS")
        forecast = ts_model.predict(future)
        last_n   = forecast.tail(data.periods)[["ds", "yhat", "yhat_lower", "yhat_upper"]]

        predictions = [
            {
                "month":     str(row["ds"])[:7],
                "predicted": round(float(row["yhat"]), 1),
                "lower":     round(float(row["yhat_lower"]), 1),
                "upper":     round(float(row["yhat_upper"]), 1)
            }
            for _, row in last_n.iterrows()
        ]

        result = {
            "forecast": predictions,
            "model":    "Prophet",
            "periods":  data.periods
        }

        logging.info(
            f"[TIMESERIES] "
            f"Periods={data.periods} | "
            f"Premier mois={predictions[0]['month']} | "
            f"Valeur={predictions[0]['predicted']}"
        )

        return result

    except Exception as e:
        logging.error(f"[TIMESERIES] Erreur : {str(e)}")
        raise HTTPException(status_code=400, detail=f"Erreur de prediction: {str(e)}")


# ====================== RETRAIN ======================
@app.post("/retrain")
def retrain_model():
    logging.info("[RETRAIN] Demande de retrainement recue")
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
        status = "success" if result.returncode == 0 else "error"

        logging.info(f"[RETRAIN] Status={status} | Return code={result.returncode}")
        if status == "error":
            logging.error(f"[RETRAIN] Erreur : {stderr[-200:]}")

        return {
            "status":     status,
            "returncode": result.returncode,
            "output":     stdout[-500:],
            "error":      stderr[-200:]
        }

    except Exception as e:
        logging.error(f"[RETRAIN] Erreur critique : {e}")
        raise HTTPException(status_code=500, detail=str(e))