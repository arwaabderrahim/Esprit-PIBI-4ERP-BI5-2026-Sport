# 🎾 Padel Analytics — MLOps Monitoring Guide
## Prometheus + Grafana + FastAPI + n8n + Docker

---

## ⚡ DÉMARRAGE RAPIDE (10 minutes)

### Étape 1 — Structure des fichiers
```
ton_projet/
├── main.py                          ← FastAPI (mis à jour)
├── retrain.py                       ← Réentraînement auto
├── simulate.py                      ← Scénarios de demo
├── docker-compose.yml               ← Tous les services
├── prometheus.yml                   ← Scraping config
├── alerts.yml                       ← Règles d'alerte
├── alertmanager.yml                 ← Notifications
├── Dockerfile                       ← Build de l'API
├── requirements.txt
├── models/                          ← Modèles .pkl
└── grafana/
    ├── provisioning/
    │   ├── datasources/prometheus.yml
    │   └── dashboards/dashboard.yml
    └── dashboards/
        └── padel_dashboard.json     ← Dashboard Grafana
```

### Étape 2 — Lancer tous les services

```bash
# Option A : Avec Docker (recommandé pour la demo)
docker-compose up -d

# Option B : Sans Docker (si Docker indisponible)
# Terminal 1 — FastAPI
uvicorn main:app --host 0.0.0.0 --port 8000 --reload

# Terminal 2 — Prometheus (télécharger sur prometheus.io)
./prometheus --config.file=prometheus.yml

# Terminal 3 — Grafana (télécharger sur grafana.com)
./bin/grafana-server
```

### Étape 3 — Vérifier que tout fonctionne

| Service | URL | Login |
|---------|-----|-------|
| FastAPI | http://localhost:8000/docs | - |
| FastAPI Metrics | http://localhost:8000/metrics | - |
| Prometheus | http://localhost:9090 | - |
| Grafana | http://localhost:3000 | admin / padel2026 |
| AlertManager | http://localhost:9093 | - |
| n8n | http://localhost:5678 | - |
| MLflow | http://localhost:5000 | - |

---

## 📊 GRAFANA — Configuration

### Importer le dashboard manuellement (si auto-provisioning ne marche pas)
1. Ouvrir http://localhost:3000
2. Login: admin / padel2026
3. Menu gauche → Dashboards → Import
4. Cliquer "Upload JSON file"
5. Sélectionner `grafana/dashboards/padel_dashboard.json`
6. Datasource: sélectionner "Prometheus"
7. Cliquer "Import"

### Ce que montre le dashboard
- **Ligne 1** : Traffic (Total Requests, Req/min, Errors, Error Rate, API Status)
- **Ligne 2** : Request Volume Over Time (timeseries)
- **Ligne 3** : Performance (P95 Latency gauge, P50 gauge, Latency percentiles)
- **Ligne 4** : Model Health (Accuracy gauge, Confidence gauge, Evolution chart)
- **Ligne 5** : Retraining & Drift (Accuracy vs baseline vs thresholds)

---

## 🧪 SCÉNARIOS DE SIMULATION (pour la demo du prof)

```bash
# Installer les dépendances
pip install requests

# Scénario 1 — High Traffic
python simulate.py --scenario 1
# → Observer dans Grafana : Request Volume spike + latence P95 augmente

# Scénario 2 — API Errors
python simulate.py --scenario 2
# → Observer dans Grafana : Error Rate spike + alerte HighErrorRate

# Scénario 3 — Model Drift
python simulate.py --scenario 3
# → Observer dans Grafana : Confidence drop + alerte LowConfidence

# Scénario 4 — Retraining automatique
python simulate.py --scenario 4
# → Observer dans MLflow + Grafana : Model Accuracy mise à jour

# TOUT en même temps (démonstration complète ~5 min)
python simulate.py --scenario all
```

---

## 🚨 ALERTES CONFIGURÉES

| Alerte | Condition | Sévérité |
|--------|-----------|----------|
| APIDown | API inaccessible > 30s | 🔴 Critical |
| HighLatency | P95 > 2s pendant 1 min | 🟡 Warning |
| CriticalLatency | P95 > 5s pendant 30s | 🔴 Critical |
| HighErrorRate | >5 erreurs / 5 min | 🟡 Warning |
| CriticalErrorRate | >20 erreurs / 5 min | 🔴 Critical |
| ModelAccuracyDrop | AUC < 0.70 | 🔴 Critical |
| ModelAccuracyWarning | AUC entre 0.70 et 0.78 | 🟡 Warning |
| LowConfidence | Confidence < 0.55 | 🟡 Warning |
| HighTraffic | >100 req/min | ℹ️ Info |

---

## 📈 MÉTRIQUES PROMETHEUS EXPOSÉES

```
api_requests_total          → Compteur total de requêtes
api_request_latency_seconds → Histogramme de latence (P50/P95/P99)
api_errors_total            → Compteur total d'erreurs
model_accuracy              → Jauge — AUC du modèle courant
model_confidence            → Jauge — Probabilité de la dernière prédiction
api_predictions_total       → Compteur par endpoint (classification/regression/...)
model_retrain_total         → Compteur de réentraînements
data_drift_score            → Jauge — Score de drift [0=normal, 1=drift total]
input_missing_values_ratio  → Jauge — Ratio de valeurs manquantes en input
```

---

## 🔍 DRIFT DETECTION

La détection du drift est implémentée dans `retrain.py` :

```python
# Drift sur les points moyens (seuil 10%)
drift_pct = abs(current_mean - ref_mean) / ref_mean * 100
if drift_pct > 10: DRIFT_DETECTED = True

# Drift sur le nombre de joueurs (seuil 5%)
player_pct = abs(current_n - ref_n) / ref_n * 100
if player_pct > 5: DRIFT_DETECTED = True
```

Si drift détecté → réentraînement automatique → MLflow log → modèle mis à jour.

---

## 📋 CRITÈRES DU PROF — Checklist

| Critère | Statut | Détail |
|---------|--------|--------|
| Prometheus monitoring | ✅ | /metrics exposé + scraping 10s |
| Grafana dashboard | ✅ | 5 sections : traffic, latence, model, retrain |
| Traffic panel | ✅ | Request rate timeseries |
| Performance panel | ✅ | P50/P95/P99 latency gauges |
| Stability panel | ✅ | Error rate % timeseries |
| Model health | ✅ | Accuracy + confidence gauges |
| Data health | ✅ | Missing values ratio + drift score |
| Alerting rules | ✅ | 9 règles dans alerts.yml |
| Drift detection | ✅ | Dans retrain.py (seuils 10% / 5%) |
| High traffic simulation | ✅ | python simulate.py --scenario 1 |
| Error simulation | ✅ | python simulate.py --scenario 2 |
| Drift simulation | ✅ | python simulate.py --scenario 3 |
| Logs | ✅ | predictions.log + retraining.log |
| Baseline comparison | ✅ | vector(0.83) dans Grafana |
| MLflow tracking | ✅ | retrain.py logs chaque run |

---

*Padel Analytics MLOps — Esprit School of Engineering 2025-2026*
