<<<<<<< HEAD
# Padel Analytics – Business Intelligence Platform

## Overview
Padel Analytics is a Business Intelligence project developed at **Esprit School of Engineering – Tunisia** (Academic Year 2025–2026).

The platform centralizes and analyzes professional and amateur padel ecosystem data to generate strategic decision-making dashboards for federations, sponsors, tournament organizers, and equipment brands.

---

## Features
- Player performance analysis over 3 years
- Tournament popularity tracking
- Equipment trend analysis
- Sponsorship analytics
- Dynamic decision dashboards
- Match reservation analytics
- Structured ETL workflows
- Industrial orchestration with Apache Airflow

---

## Tech Stack

### Data Integration
- Talend

### Data Warehouse
- MySQL

### Orchestration
- Apache Airflow

### Automation
- N8N

### Visualization
- Power BI

### Backend
- Django / Flask

### Frontend
- Angular

---

## Architecture

Data Sources → Talend ETL → MySQL Data Warehouse → Power BI Dashboards  
Orchestration handled by Apache Airflow (Master DAG & Sub-jobs)  
Automation workflows managed by N8N  

---

## KPIs
- Win rate
- Ranking evolution
- Tournament participation rate
- Prize money distribution
- Equipment popularity
- Brand visibility metrics

---

## Academic Context
Developed as part of the Business Intelligence Integration Project (PIBI)  
**Esprit School of Engineering – Tunisia**  
4ERP-BI5 | Academic Year 2025–2026  

---

## Getting Started
1. Clone the repository  
2. Configure the MySQL database  
3. Execute Talend ETL jobs  
4. Run Airflow orchestration  
5. Open Power BI dashboards  

---

## Contributors
- Arwa Abderrahim
- Amenallah Attyoui
- Eya Mejri
- Abir Mannai
- Ahmed Karoui
- Yassine Lahmaier

---

## Acknowledgments
Supervised under the academic framework of **Esprit School of Engineering – Tunisia**.
=======
# 🎾 Padel Analytics — MLOps Pipeline Documentation

## Vue d'ensemble

Ce projet implémente un pipeline MLOps complet pour prédire les résultats de matchs de Padel.
Il combine **n8n** pour l'automatisation, **FastAPI** pour le serving des modèles ML, **Docker** pour la conteneurisation, et **MLflow** pour le tracking des expériences.

---

## Architecture complète

```
MySQL (dw_padel_analytics)
        ↓
Notebooks ML (entraînement)
        ↓
FastAPI (serving 4 modèles)
        ↓
n8n (automatisation)
   ├── Cron → /retrain (hebdomadaire)
   └── Webhook → /predict/classification (temps réel)
        ↓
MLflow (tracking des runs)
        ↓
Web App (interface utilisateur)
```

---

## Structure du projet

```
mon_projet/
│
├── main.py                          ← Serveur FastAPI (4 endpoints ML)
├── retrain.py                       ← Pipeline de réentraînement automatique
├── index.html                       ← Web App (interface utilisateur)
├── predictions.log                  ← Logs des prédictions
├── retraining.log                   ← Logs des réentraînements
├── workflow_padel_pipeline.json     ← Export du workflow n8n
│
├── models/
│   ├── classification_model.pkl     ← Logistic Regression (Win/Loss)
│   ├── classification_features.pkl  ← Features du modèle
│   ├── regression_model.pkl         ← Random Forest (Prize Money)
│   ├── regression_features.pkl
│   ├── clustering_model.pkl         ← K-Means (Profil joueur)
│   ├── clustering_scaler.pkl
│   ├── timeseries_model.pkl         ← Prophet (Prévision matchs)
│   ├── reference_stats.json         ← Stats de référence (drift detection)
│   ├── last_retrain_report.json     ← Rapport du dernier réentraînement
│   └── versions/                   ← Historique des modèles versionnés
│       ├── classification_model_20260418_0800.pkl
│       └── classification_model_20260425_0800.pkl
│
└── notebooks/
    ├── 01_data_preparation.ipynb
    ├── 02_classification_BC.ipynb
    ├── 03_regression_D.ipynb
    ├── 04_clustering_E.ipynb
    └── 05_timeseries_F.ipynb
```

---

## Partie 1 — Workflow n8n

### Description du workflow

Le workflow **"Padel Analytics - ML Pipeline"** contient 2 pipelines dans le même canvas :

---

### Pipeline 1 — Retraining automatique (Cron)

```
Schedule Trigger (hebdomadaire)
        ↓
HTTP Request → POST /retrain
        ↓
IF (status = success ?)
   ↙                    ↘
Edit Fields           Edit Fields1
"Modèle réentraîné"   "Erreur retraining"
```

#### Détail des nodes

| Node | Type | Rôle | Configuration |
|------|------|------|---------------|
| **Schedule Trigger** | Trigger | Déclenche le workflow automatiquement | Toutes les semaines |
| **HTTP Request** | Action | Appelle FastAPI pour réentraîner le modèle | POST `http://host.docker.internal:8000/retrain` — Retry: 3 fois, 5s entre chaque |
| **IF** | Logic | Vérifie si le réentraînement a réussi | `status == "success"` |
| **Edit Fields** | Output | Confirme le succès | status = "Modèle réentraîné", auc = output |
| **Edit Fields1** | Output | Signale l'erreur | status = "Erreur retraining", detail = error |

---

### Pipeline 2 — Inference en temps réel (Webhook)

```
Webhook (POST /padel-prediction)
        ↓
Edit Fields2 (prépare les données)
        ↓
HTTP Request1 → POST /predict/classification
        ↓
Edit Fields3 (formate le résultat)
```

#### Détail des nodes

| Node | Type | Rôle | Configuration |
|------|------|------|---------------|
| **Webhook** | Trigger | Reçoit des données de match en temps réel | POST `http://localhost:5678/webhook/padel-prediction` |
| **Edit Fields2** | Transform | Extrait les features du payload reçu | pts_diff, rank_diff, Pts_B |
| **HTTP Request1** | Action | Appelle FastAPI pour la prédiction | POST `http://host.docker.internal:8000/predict/classification` — Body JSON avec 16 features |
| **Edit Fields3** | Output | Formate le résultat final | result = label, probability = win_probability |

---

### Settings du workflow

```json
{
  "active": true,
  "errorWorkflow": "zeiOzJ0RO5wdm1sx",
  "executionOrder": "v1"
}
```

- **Active** : workflow publié et en production
- **Error Workflow** : connecté au workflow Error Handler (email automatique si erreur)

---

## Partie 2 — FastAPI (main.py)

### Endpoints disponibles

| Method | Endpoint | Description | Modèle |
|--------|----------|-------------|--------|
| GET | `/` | Page d'accueil | - |
| GET | `/health` | Statut des modèles | - |
| POST | `/predict/classification` | Prédit Win ou Loss | Logistic Regression |
| POST | `/predict/regression` | Prédit le Prize Money | Random Forest |
| POST | `/predict/clustering` | Segmente le joueur | K-Means |
| POST | `/predict/timeseries` | Prévision des matchs | Prophet |
| POST | `/retrain` | Réentraîne le modèle | - |

### Exemple de requête — Classification

```bash
curl -X POST http://localhost:8000/predict/classification \
-H "Content-Type: application/json" \
-d '{
  "Pts_A": 1500, "Pos_A": 5, "Move_A": 2, "Level_A": "P1",
  "Pts_B": 1200, "Pos_B": 8, "Move_B": -1, "Level_B": "P2",
  "rank_diff": 3, "pts_diff": 300,
  "Year": 2026, "Month": 4, "Quarter": 2,
  "IsWeekend": 0, "Gender": "M", "Match_type": "singles"
}'
```

### Exemple de réponse

```json
{
  "prediction": 1,
  "label": "Win",
  "win_probability": 0.7234,
  "model": "Logistic Regression"
}
```

---

## Partie 3 — retrain.py

### Pipeline de réentraînement

```
1. Connexion MySQL (dw_padel_analytics)
        ↓
2. Chargement des données
   (dim_player_ofc + fact_performance)
        ↓
3. Détection du Data Drift
   - Points moyen : seuil 10%
   - Nombre joueurs : seuil 5%
        ↓
4. Décision : réentraîner ou non ?
        ↓
5. Préparation du dataset (80/20 split)
        ↓
6. Entraînement + MLflow tracking
   - Logistic Regression Pipeline
   - ROC-AUC calculé
        ↓
7. Sauvegarde + Versioning
   - models/classification_model.pkl (courant)
   - models/versions/classification_model_YYYYMMDD_HHmm.pkl
   - models/reference_stats.json (nouvelle référence drift)
   - models/last_retrain_report.json (rapport complet)
```

### Commandes

```bash
# Réentraînement normal (si drift détecté)
python retrain.py

# Forcer le réentraînement
python retrain.py --force
```

---

## Partie 4 — MLflow

### Experiment : Padel-Analytics-Classification

| Paramètre | Valeur |
|-----------|--------|
| model | LogisticRegression |
| C | 1.0 |
| max_iter | 1000 |
| test_size | 0.2 |
| n_features | 6 |

| Métrique | Description |
|----------|-------------|
| roc_auc | Score ROC-AUC sur le test set |
| n_examples | Nombre total d'exemples |
| n_train | Exemples d'entraînement (80%) |
| n_test | Exemples de test (20%) |

### Lancer l'interface MLflow

```bash
mlflow ui --port 5000
# Ouvrir : http://localhost:5000
```

---

## Partie 5 — Docker

### Containers actifs

| Container | Image | Port | Rôle |
|-----------|-------|------|------|
| n8n | n8nio/n8n | 5678 | Automatisation des workflows |
| padel-analytics | n8n-padel-analytics | 8000 | FastAPI + Modèles ML |

### Pourquoi `host.docker.internal` ?

n8n tourne dans Docker et ne peut pas accéder à `localhost` de Windows directement.
`host.docker.internal` est l'adresse spéciale qui permet au container Docker d'accéder
aux services qui tournent sur la machine hôte Windows.

---

## Partie 6 — Error Handling

### Ce qui est en place

| Mécanisme | Description |
|-----------|-------------|
| **Retries n8n** | 3 tentatives, 5 secondes entre chaque |
| **Error Workflow** | Workflow séparé déclenché si erreur |
| **Email notification** | Email envoyé via SMTP Gmail si erreur |
| **predictions.log** | Toutes les prédictions loggées avec timestamp |
| **retraining.log** | Tous les réentraînements loggés avec AUC |

---

## Démarrage du projet

```bash
# 1. Démarrer MySQL (XAMPP)
# Ouvrir XAMPP → Start MySQL

# 2. Démarrer FastAPI
cd C:\Users\Asus\Desktop\n8n
uvicorn main:app --host 0.0.0.0 --port 8000 --reload

# 3. Vérifier Docker
docker ps
# → n8n (5678) + padel-analytics (8000)

# 4. Lancer MLflow UI
mlflow ui --port 5000

# 5. Ouvrir les interfaces
# FastAPI docs : http://127.0.0.1:8000/docs
# n8n          : http://localhost:5678
# MLflow       : http://localhost:5000
# Web App      : ouvrir index.html
```

---

## Modèles ML utilisés

| Modèle | Algorithme | Target | Métrique |
|--------|-----------|--------|----------|
| Classification | Logistic Regression | Win/Loss | ROC-AUC = 0.83 |
| Régression | Random Forest | Prize Money (€) | R² = 0.9883 |
| Clustering | K-Means | Profil joueur | Silhouette score |
| Time Series | Prophet | Matchs par mois | MAPE = 67.47% |

---

*Padel Analytics MLOps Pipeline — FastAPI + n8n + Docker + MLflow*
>>>>>>> a7165ff (MLOps Pipeline - FastAPI + n8n + Docker + MLflow)
