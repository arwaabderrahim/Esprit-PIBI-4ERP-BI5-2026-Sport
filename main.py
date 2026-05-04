"""
🎾 Padel Analytics ML API — FastAPI + Prometheus Monitoring
===========================================================
Endpoints :
  GET  /            → Info
  GET  /health      → Statut des modèles
  GET  /metrics     → Métriques Prometheus
  POST /predict/classification  → Win/Loss prediction
  POST /predict/regression      → Prize money prediction
  POST /predict/clustering      → Player segment
  POST /predict/timeseries      → Forecast
  POST /retrain                 → Déclenche le réentraînement
"""

from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import Response, HTMLResponse
from pydantic import BaseModel

import joblib
import numpy as np
import pandas as pd
import json
import logging
import time
import os
from datetime import datetime

# ── Prometheus ──────────────────────────────────────────────────
from prometheus_client import (
    Counter, Histogram, Gauge, Summary, generate_latest, REGISTRY
)

# ── ML ───────────────────────────────────────────────────────────
from sklearn.preprocessing import LabelEncoder
from sklearn.impute import SimpleImputer

# ── Logging ──────────────────────────────────────────────────────
logging.basicConfig(
    filename="predictions.log",
    level=logging.INFO,
    format="%(asctime)s | %(levelname)s | %(message)s"
)
console = logging.StreamHandler()
console.setLevel(logging.INFO)
logging.getLogger("").addHandler(console)

# ═══════════════════════════════════════════════════════════════
# PROMETHEUS METRICS
# ═══════════════════════════════════════════════════════════════
REQUEST_COUNT = Counter(
    "api_requests_total",
    "Total number of API requests"
)
REQUEST_LATENCY = Histogram(
    "api_request_latency_seconds",
    "API request latency in seconds",
    buckets=[0.01, 0.05, 0.1, 0.25, 0.5, 1.0, 2.0, 5.0, 10.0]
)
ERROR_COUNT = Counter(
    "api_errors_total",
    "Total number of API errors"
)
MODEL_ACCURACY = Gauge(
    "model_accuracy",
    "Current model ROC-AUC accuracy"
)
MODEL_CONFIDENCE = Gauge(
    "model_confidence",
    "Prediction confidence (win probability) of last prediction"
)
PREDICTIONS_BY_ENDPOINT = Counter(
    "api_predictions_total",
    "Number of predictions per endpoint",
    ["endpoint"]
)
RETRAIN_COUNT = Counter(
    "model_retrain_total",
    "Number of model retraining events"
)
DATA_DRIFT_SCORE = Gauge(
    "data_drift_score",
    "Current data drift score (0=no drift, 1=full drift)"
)
MISSING_VALUES_GAUGE = Gauge(
    "input_missing_values_ratio",
    "Ratio of missing/zero values in last prediction input"
)

# ═══════════════════════════════════════════════════════════════
# APP SETUP
# ═══════════════════════════════════════════════════════════════
app = FastAPI(
    title="🎾 Padel Analytics ML API",
    description="End-to-end ML serving for Padel match analytics",
    version="2.0.0"
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# ── Prometheus middleware ────────────────────────────────────────
@app.middleware("http")
async def prometheus_middleware(request, call_next):
    start = time.time()
    REQUEST_COUNT.inc()
    try:
        response = await call_next(request)
        return response
    except Exception:
        ERROR_COUNT.inc()
        raise
    finally:
        REQUEST_LATENCY.observe(time.time() - start)

# ═══════════════════════════════════════════════════════════════
# LOAD MODELS
# ═══════════════════════════════════════════════════════════════
clf, clf_features, reg, reg_features, clu, scaler, ts_model = [None]*7

try:
    clf = joblib.load("models/classification_model.pkl")
    clf_features = joblib.load("models/classification_features.pkl")
    logging.info("[STARTUP] Modele Classification charge avec succes")
except Exception as e:
    logging.warning(f"[STARTUP] Classification model not found: {e}")

try:
    reg = joblib.load("models/regression_model.pkl")
    reg_features = joblib.load("models/regression_features.pkl")
    logging.info("[STARTUP] Modele Regression charge avec succes")
except Exception as e:
    logging.warning(f"[STARTUP] Regression model not found: {e}")

try:
    clu = joblib.load("models/clustering_model.pkl")
    scaler = joblib.load("models/clustering_scaler.pkl")
    logging.info("[STARTUP] Modele Clustering charge avec succes")
except Exception as e:
    logging.warning(f"[STARTUP] Clustering model not found: {e}")

try:
    from prophet.serialize import model_from_json
    with open("models/timeseries_model.json", "r") as f:
        ts_model = model_from_json(f.read())
    logging.info("[STARTUP] Modele Time Series charge avec succes")
except Exception as e:
    logging.warning(f"[STARTUP] Time series model not found: {e}")

# ── Load accuracy from last retrain report ───────────────────────
try:
    with open("models/last_retrain_report.json") as f:
        report = json.load(f)
        MODEL_ACCURACY.set(report.get("roc_auc", 0.83))
except:
    MODEL_ACCURACY.set(0.83)

# ═══════════════════════════════════════════════════════════════
# INPUT MODELS
# ═══════════════════════════════════════════════════════════════
class ClassificationInput(BaseModel):
    Pts_A: float; Pos_A: float; Move_A: float; Level_A: str
    Pts_B: float; Pos_B: float; Move_B: float; Level_B: str
    rank_diff: float; pts_diff: float
    Year: int; Month: int; Quarter: int; IsWeekend: int
    Gender: str; Match_type: str

class RegressionInput(BaseModel):
    Points__Winner: float; Points__Loser: float
    Points_W1: float; Position_W1: float
    points_diff: float; nb_sets: float; is_3sets: int
    Year: int; Month: int; Quarter: int; IsWeekend: int
    Gender: str; Match_type: str; Level_W1: str

class ClusteringInput(BaseModel):
    Points: float; Position: float; Move: float
    total_wins: float; total_loss: float; total_matchs: float
    win_rate: float; Level: str; Genre: str

class TimeSeriesInput(BaseModel):
    periods: int = 6

# ═══════════════════════════════════════════════════════════════
# ROUTES
# ═══════════════════════════════════════════════════════════════

@app.get("/", response_class=HTMLResponse)
def root():
    return """
    <html><body style="font-family:Arial;background:#111;color:#eee;padding:2rem;">
    <h1>🎾 Padel Analytics ML API</h1>
    <p>Version 2.0 — FastAPI + Prometheus + MLflow</p>
    <ul>
      <li><a href="/docs" style="color:#1d9e75">/docs</a> — Interactive API docs</li>
      <li><a href="/health" style="color:#1d9e75">/health</a> — Models status</li>
      <li><a href="/metrics" style="color:#1d9e75">/metrics</a> — Prometheus metrics</li>
    </ul>
    </body></html>
    """

@app.get("/health")
def health():
    try:
        with open("models/last_retrain_report.json") as f:
            report = json.load(f)
            auc = report.get("roc_auc", 0.83)
            MODEL_ACCURACY.set(auc)
    except:
        auc = 0.83

    return {
        "status": "OK",
        "time": datetime.now().isoformat(),
        "models": {
            "classification": clf is not None,
            "regression": reg is not None,
            "clustering": clu is not None,
            "timeseries": ts_model is not None
        },
        "model_accuracy": auc
    }

@app.get("/metrics")
def metrics():
    return Response(generate_latest(), media_type="text/plain")

# ── Classification ───────────────────────────────────────────────
@app.post("/predict/classification")
def predict_classification(data: ClassificationInput):
    if clf is None:
        ERROR_COUNT.inc()
        raise HTTPException(503, "Classification model not available")

    try:
        PREDICTIONS_BY_ENDPOINT.labels(endpoint="classification").inc()
        X = pd.DataFrame([data.dict()])

        # Compute data quality metric
        zeros = (X.select_dtypes(include='number') == 0).sum().sum()
        total = X.select_dtypes(include='number').size
        missing_ratio = zeros / (total + 1e-9)
        MISSING_VALUES_GAUGE.set(missing_ratio)

        # Drift indicator: if all numeric zeros → likely degraded input
        if missing_ratio > 0.8:
            DATA_DRIFT_SCORE.set(0.9)
        else:
            DATA_DRIFT_SCORE.set(0.1)

        for c in ["Level_A", "Level_B", "Gender", "Match_type"]:
            X[c] = LabelEncoder().fit_transform(X[c].astype(str))

        X = pd.DataFrame(SimpleImputer(strategy="median").fit_transform(X), columns=X.columns)
        X = X[clf_features]

        pred  = int(clf.predict(X)[0])
        proba = float(clf.predict_proba(X)[0][1])

        MODEL_CONFIDENCE.set(proba)

        label = "Win ✅" if pred == 1 else "Loss ❌"
        logging.info(
            f"[CLASSIFICATION] Pts_A={data.Pts_A} | Pts_B={data.Pts_B} | "
            f"pts_diff={data.pts_diff} | rank_diff={data.rank_diff} | "
            f"Prediction={label} | Probabilite={proba:.4f}"
        )

        return {
            "prediction": pred,
            "label": label,
            "win_probability": round(proba, 4),
            "model": "Classification Pipeline (LR + RF)"
        }

    except Exception as e:
        ERROR_COUNT.inc()
        logging.error(f"[CLASSIFICATION] Error: {e}")
        raise HTTPException(500, f"Prediction error: {str(e)}")

# ── Regression ───────────────────────────────────────────────────
@app.post("/predict/regression")
def predict_regression(data: RegressionInput):
    if reg is None:
        ERROR_COUNT.inc()
        raise HTTPException(503, "Regression model not available")
    try:
        PREDICTIONS_BY_ENDPOINT.labels(endpoint="regression").inc()
        X = pd.DataFrame([data.dict()])

        for c in ["Gender", "Match_type", "Level_W1"]:
            X[c] = LabelEncoder().fit_transform(X[c].astype(str))

        X = pd.DataFrame(SimpleImputer(strategy="median").fit_transform(X), columns=X.columns)
        if reg_features:
            X = X[reg_features]

        pred_log = float(reg.predict(X)[0])
        pred_eur = float(np.expm1(pred_log))  # inverse log1p

        logging.info(
            f"[REGRESSION] Winner_Points={data.Points__Winner} | "
            f"Loser_Points={data.Points__Loser} | Prize_Money={pred_eur:.2f} EUR"
        )
        return {
            "prediction_log": round(pred_log, 4),
            "prize_money_eur": round(pred_eur, 2)
        }
    except Exception as e:
        ERROR_COUNT.inc()
        logging.error(f"[REGRESSION] Error: {e}")
        raise HTTPException(500, f"Prediction error: {str(e)}")

# ── Clustering ───────────────────────────────────────────────────
CLUSTER_PROFILES = {
    0: "Joueur challenger (classement 100-500)",
    1: "Joueur elite (top classement)",
    2: "Joueur amateur (classement > 500)",
}

@app.post("/predict/clustering")
def predict_clustering(data: ClusteringInput):
    if clu is None:
        ERROR_COUNT.inc()
        raise HTTPException(503, "Clustering model not available")
    try:
        PREDICTIONS_BY_ENDPOINT.labels(endpoint="clustering").inc()
        X = pd.DataFrame([data.dict()])

        for c in ["Level", "Genre"]:
            X[c] = LabelEncoder().fit_transform(X[c].astype(str))

        X = pd.DataFrame(SimpleImputer(strategy="median").fit_transform(X), columns=X.columns)
        X_sc = scaler.transform(X)
        cluster = int(clu.predict(X_sc)[0])
        profil = CLUSTER_PROFILES.get(cluster, f"Cluster {cluster}")

        logging.info(
            f"[CLUSTERING] Points={data.Points} | Position={data.Position} | "
            f"win_rate={data.win_rate:.2f} | Cluster={cluster} | Profil={profil}"
        )
        return {"cluster": cluster, "profil": profil}

    except Exception as e:
        ERROR_COUNT.inc()
        logging.error(f"[CLUSTERING] Error: {e}")
        raise HTTPException(500, f"Prediction error: {str(e)}")

# ── Time Series ──────────────────────────────────────────────────
@app.post("/predict/timeseries")
def predict_timeseries(data: TimeSeriesInput):
    if ts_model is None:
        ERROR_COUNT.inc()
        raise HTTPException(503, "Time series model not available")
    try:
        PREDICTIONS_BY_ENDPOINT.labels(endpoint="timeseries").inc()
        future = ts_model.make_future_dataframe(periods=data.periods, freq="MS")
        forecast = ts_model.predict(future)
        tail = forecast.tail(data.periods)[["ds", "yhat", "yhat_lower", "yhat_upper"]]

        logging.info(
            f"[TIMESERIES] Periods={data.periods} | "
            f"Premier mois={tail['ds'].iloc[0].strftime('%Y-%m')} | "
            f"Valeur={tail['yhat'].iloc[0]:.1f}"
        )
        return tail.to_dict(orient="records")

    except Exception as e:
        ERROR_COUNT.inc()
        logging.error(f"[TIMESERIES] Error: {e}")
        raise HTTPException(500, f"Prediction error: {str(e)}")

# ── Retrain ──────────────────────────────────────────────────────
@app.post("/retrain")
def retrain():
    import subprocess
    logging.info("[RETRAIN] Demande de retrainement recue")
    RETRAIN_COUNT.inc()
    try:
        result = subprocess.run(
            ["python", "retrain.py"],
            capture_output=True, text=True,
            encoding="utf-8", errors="replace",
            cwd=os.getcwd()
        )
        status = "success" if result.returncode == 0 else "error"
        logging.info(f"[RETRAIN] Status={status} | Return code={result.returncode}")

        # Reload accuracy from new report
        try:
            with open("models/last_retrain_report.json") as f:
                report = json.load(f)
                new_auc = report.get("roc_auc", 0.83)
                MODEL_ACCURACY.set(new_auc)
                logging.info(f"[RETRAIN] New accuracy: {new_auc:.4f}")
        except:
            pass

        return {
            "status": status,
            "returncode": result.returncode,
            "output": (result.stdout or "")[-500:],
            "error":  (result.stderr or "")[-200:]
        }
    except Exception as e:
        ERROR_COUNT.inc()
        logging.error(f"[RETRAIN] Exception: {e}")
        raise HTTPException(500, detail=str(e))
