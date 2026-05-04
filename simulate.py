"""
🎾 Padel Analytics — Simulation Scenarios
==========================================
Ce script simule 4 scénarios de production pour la démonstration devant le prof.

USAGE:
    python simulate.py --scenario 1   # High Traffic
    python simulate.py --scenario 2   # API Errors
    python simulate.py --scenario 3   # Model Drift
    python simulate.py --scenario all # Tous les scénarios (15 min)

PRÉREQUIS : pip install requests
"""

import requests
import time
import random
import argparse
import threading
from datetime import datetime

API_URL = "http://localhost:8000"

MATCH_PAYLOAD = {
    "Pts_A": 1500, "Pos_A": 5, "Move_A": 2, "Level_A": "P1",
    "Pts_B": 1200, "Pos_B": 8, "Move_B": -1, "Level_B": "P2",
    "rank_diff": 3, "pts_diff": 300,
    "Year": 2026, "Month": 5, "Quarter": 2,
    "IsWeekend": 0, "Gender": "M", "Match_type": "singles"
}

def log(msg):
    print(f"[{datetime.now().strftime('%H:%M:%S')}] {msg}")

# ──────────────────────────────────────────────────────────────
# SCÉNARIO 1 — HIGH TRAFFIC
# Objectif : observer l'impact sur la latence et le taux de req
# ──────────────────────────────────────────────────────────────
def scenario_high_traffic(duration_s=60, rps=20):
    log("=" * 55)
    log("🚀 SCÉNARIO 1 — HIGH TRAFFIC")
    log(f"   Envoi de {rps} requêtes/seconde pendant {duration_s}s")
    log(f"   → Observer dans Grafana : Request Volume + P95 Latency")
    log("=" * 55)

    start = time.time()
    count = 0
    errors = 0

    while time.time() - start < duration_s:
        try:
            r = requests.post(f"{API_URL}/predict/classification",
                              json=MATCH_PAYLOAD, timeout=5)
            if r.status_code == 200:
                count += 1
            else:
                errors += 1
        except Exception:
            errors += 1

        time.sleep(1 / rps)

    log(f"✅ Scénario 1 terminé : {count} OK | {errors} erreurs en {duration_s}s")
    log(f"   → Vérifier dans Grafana : latence P95 augmentée ?")


# ──────────────────────────────────────────────────────────────
# SCÉNARIO 2 — API ERRORS
# Objectif : générer des erreurs pour tester les alertes
# ──────────────────────────────────────────────────────────────
def scenario_api_errors(n=30):
    log("=" * 55)
    log("💥 SCÉNARIO 2 — API ERRORS")
    log(f"   Envoi de {n} requêtes malformées intentionnellement")
    log(f"   → Observer dans Grafana : Error Rate spike")
    log("=" * 55)

    bad_payloads = [
        {},                          # payload vide
        {"Pts_A": "invalid"},        # mauvais type
        {"Pts_A": 1500},             # payload incomplet (15 features manquantes)
        {"wrong_field": 999},        # champ inexistant
        None                         # None → erreur sérialisation
    ]

    errors = 0
    for i in range(n):
        payload = random.choice(bad_payloads)
        try:
            r = requests.post(f"{API_URL}/predict/classification",
                              json=payload if payload else {},
                              timeout=3)
            if r.status_code != 200:
                errors += 1
                log(f"   [{i+1}/{n}] ❌ Error {r.status_code}")
        except Exception as e:
            errors += 1
            log(f"   [{i+1}/{n}] ❌ Exception: {str(e)[:50]}")
        time.sleep(0.3)

    log(f"✅ Scénario 2 terminé : {errors}/{n} erreurs générées")
    log(f"   → Vérifier dans Grafana : alertes HighErrorRate déclenchées ?")


# ──────────────────────────────────────────────────────────────
# SCÉNARIO 3 — MODEL DRIFT SIMULATION
# Objectif : simuler une dégradation du modèle
# (On force accuracy basse via l'endpoint metrics)
# ──────────────────────────────────────────────────────────────
def scenario_model_drift(duration_s=90):
    log("=" * 55)
    log("📉 SCÉNARIO 3 — MODEL DRIFT SIMULATION")
    log(f"   Envoi de features dégradées (données incohérentes)")
    log(f"   → Observer dans Grafana : Confidence drop")
    log("=" * 55)

    # Phase 1 : données normales (baseline)
    log("   Phase 1 (30s) : données normales...")
    t0 = time.time()
    while time.time() - t0 < 30:
        r = requests.post(f"{API_URL}/predict/classification",
                          json=MATCH_PAYLOAD, timeout=5)
        log(f"   Normal → proba={r.json().get('win_probability',0):.3f}")
        time.sleep(3)

    # Phase 2 : données dégradées (simulant le drift)
    log("\n   Phase 2 (60s) : données dégradées (drift simulé)...")
    drift_payload = {
        "Pts_A": 0.0, "Pos_A": 0.0, "Move_A": 0.0, "Level_A": "Unknown",
        "Pts_B": 0.0, "Pos_B": 0.0, "Move_B": 0.0, "Level_B": "Unknown",
        "rank_diff": 0.0, "pts_diff": 0.0,
        "Year": 2000, "Month": 0, "Quarter": 0,
        "IsWeekend": 0, "Gender": "Unknown", "Match_type": "Unknown"
    }
    t1 = time.time()
    while time.time() - t1 < 60:
        try:
            r = requests.post(f"{API_URL}/predict/classification",
                              json=drift_payload, timeout=5)
            if r.status_code == 200:
                proba = r.json().get('win_probability', 0.5)
                log(f"   Drift  → proba={proba:.3f} {'⚠️ LOW CONFIDENCE' if proba < 0.55 else ''}")
        except Exception as e:
            log(f"   ❌ {e}")
        time.sleep(3)

    log(f"✅ Scénario 3 terminé")
    log(f"   → Vérifier dans Grafana : confidence < 0.55 ? Alert LowConfidence ?")


# ──────────────────────────────────────────────────────────────
# SCÉNARIO 4 — RETRAINING AUTOMATIQUE
# Objectif : déclencher le retrain et observer les métriques
# ──────────────────────────────────────────────────────────────
def scenario_retraining():
    log("=" * 55)
    log("🔄 SCÉNARIO 4 — RETRAINING AUTOMATIQUE")
    log("   Déclenchement du endpoint /retrain")
    log("=" * 55)

    log("   Avant retrain — état actuel :")
    try:
        h = requests.get(f"{API_URL}/health", timeout=5).json()
        log(f"   Models: {h.get('models', {})}")
    except:
        log("   ⚠️ Health check failed")

    log("\n   Déclenchement du retrain...")
    try:
        r = requests.post(f"{API_URL}/retrain", timeout=120)
        data = r.json()
        log(f"   Status: {data.get('status')}")
        log(f"   Output: {data.get('output','')[:200]}")
    except Exception as e:
        log(f"   ❌ Retrain error: {e}")

    log("\n   Post retrain — état des modèles :")
    try:
        h = requests.get(f"{API_URL}/health", timeout=5).json()
        log(f"   {h}")
    except:
        pass

    log("✅ Scénario 4 terminé → Vérifier MLflow pour le nouveau run")


# ──────────────────────────────────────────────────────────────
# BASELINE NORMAL — Trafic normal de fond
# ──────────────────────────────────────────────────────────────
def baseline_traffic(duration_s=300):
    """Simule du trafic normal en arrière-plan"""
    log("📊 Trafic de fond (1 req/5s)...")
    t0 = time.time()
    endpoints = [
        ("/predict/classification", MATCH_PAYLOAD),
        ("/health", None),
    ]
    while time.time() - t0 < duration_s:
        ep, payload = random.choice(endpoints)
        try:
            if payload:
                requests.post(f"{API_URL}{ep}", json=payload, timeout=5)
            else:
                requests.get(f"{API_URL}{ep}", timeout=5)
        except:
            pass
        time.sleep(5)


# ──────────────────────────────────────────────────────────────
# MAIN
# ──────────────────────────────────────────────────────────────
if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Padel Analytics — Simulation Scenarios")
    parser.add_argument("--scenario", type=str, default="1",
                        choices=["1", "2", "3", "4", "all"],
                        help="1=High Traffic, 2=Errors, 3=Drift, 4=Retrain, all=Tous")
    args = parser.parse_args()

    log("🎾 PADEL ANALYTICS — SIMULATION DÉMARRÉE")
    log(f"   API URL  : {API_URL}")
    log(f"   Scenario : {args.scenario}")

    # Vérifier que l'API est disponible
    try:
        r = requests.get(f"{API_URL}/health", timeout=5)
        log(f"✅ API accessible — status: {r.status_code}")
    except Exception as e:
        log(f"❌ API inaccessible : {e}")
        log("   Vérifier que 'uvicorn main:app --port 8000' est lancé")
        exit(1)

    if args.scenario == "1":
        scenario_high_traffic(duration_s=60, rps=10)

    elif args.scenario == "2":
        scenario_api_errors(n=30)

    elif args.scenario == "3":
        scenario_model_drift(duration_s=90)

    elif args.scenario == "4":
        scenario_retraining()

    elif args.scenario == "all":
        log("\n🎬 MODE DÉMONSTRATION COMPLÈTE (toutes les scènes)")
        log("   Durée estimée : ~5 minutes\n")

        # Trafic de fond en thread séparé
        bg = threading.Thread(target=baseline_traffic, args=(300,), daemon=True)
        bg.start()

        log("\n⏱️  Début dans 5 secondes...")
        time.sleep(5)

        log("\n--- SCÈNE 1 : Trafic normal (baseline) ---")
        time.sleep(30)

        log("\n--- SCÈNE 2 : High Traffic ---")
        scenario_high_traffic(duration_s=60, rps=15)

        log("\n--- SCÈNE 3 : API Errors ---")
        scenario_api_errors(n=25)

        log("\n--- SCÈNE 4 : Model Drift ---")
        scenario_model_drift(duration_s=90)

        log("\n--- SCÈNE 5 : Retraining ---")
        scenario_retraining()

        log("\n✅ DÉMONSTRATION COMPLÈTE TERMINÉE")
        log("   → Vérifier Grafana pour observer tous les scénarios")

    log("\n🏁 Simulation terminée.")
    log("   Grafana : http://localhost:3000  (admin / padel2026)")
    log("   Prometheus : http://localhost:9090")
    log("   AlertManager : http://localhost:9093")
