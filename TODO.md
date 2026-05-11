# TODO - Padel Frontend x Backend Integration

- [ ] Audit remaining frontend dashboards/components for missing API calls (regression/clustering/timeseries/retrain, federation/sponsor pages).
- [ ] Implement missing UI wiring to call backend endpoints:
  - [ ] POST /predict/regression
  - [ ] POST /predict/clustering
  - [ ] POST /predict/timeseries
  - [ ] POST /retrain
- [ ] Fix route mismatches if needed (shell nav + router paths).
- [ ] Ensure CORS + baseUrl/proxy work correctly in docker-compose deployment.
- [ ] Build frontend and verify it loads API endpoints successfully (health check + one prediction).
- [ ] Run backend + frontend together with docker-compose and validate end-to-end.

