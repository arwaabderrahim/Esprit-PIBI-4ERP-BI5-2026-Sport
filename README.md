# Padel Analytics – Business Intelligence Platform

## Overview
Padel Analytics is a Business Intelligence platform developed at **Esprit School of Engineering – Tunisia** (Academic Year 2025–2026).

The project aims to centralize, transform, and analyze professional and amateur padel ecosystem data in order to provide strategic insights to federations, sponsors, equipment brands, tournament organizers, and sports analysts.

The system integrates sports, equipment, and business data to generate dynamic decision dashboards.

---

## Objectives

- Analyze player performance over the last 3 years
- Identify equipment usage trends
- Evaluate tournament popularity and impact
- Assist brands in targeting high-potential players
- Support organizers in optimizing tournaments
- Manage match reservation system
- Produce interactive BI dashboards

---

## Key Performance Indicators (KPIs)

### Sport KPIs – Players
- Number of matches played
- Win rate
- Average ranking
- Ranking evolution
- Performance by surface
- Performance by tournament
- Points-to-match ratio

### Tournament KPIs
- Number of participants per tournament
- Nationality distribution
- Monthly participation rate
- Estimated audience
- Top-performing players per tournament
- Player loyalty rate
- Total prize money

### Equipment KPIs
- Most used brands
- Equipment distribution by type
- Average racket price
- Sponsored players per brand
- Equipment-performance correlation

### Business & Marketing KPIs
- Brand visibility
- Player popularity
- Tournament popularity
- Padel growth rate over time

---

## Architecture

Data Sources → Talend ETL → Data Warehouse → Power BI Dashboards  
                 ↓  
           Apache Airflow (Orchestration)  
                 ↓  
              N8N (Automation)

Frontend: Angular  
Backend: Django / Flask  

---

## Tech Stack

### Data Integration
- Talend

### Data Storage
- PostgreSQL / Data Warehouse

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

## Academic Context
Developed as part of the Business Intelligence Integration Project (PIBI)  
**Esprit School of Engineering – Tunisia**  
Academic Year: 2025–2026  
Class: 4ERP-BI5

---

## Getting Started

### Prerequisites
- Talend Studio
- PostgreSQL
- Apache Airflow
- Power BI
- Node.js (Angular)
- Python (Django / Flask)

### Setup
1. Clone repository
2. Configure database
3. Execute ETL jobs
4. Run Airflow orchestration
5. Open Power BI dashboards

---

## Project Structure

```
/
├── /backend              # Django/Flask backend application
├── /frontend             # Angular frontend application
├── /talend-jobs          # Talend ETL jobs
├── /airflow-dags         # Apache Airflow DAGs
├── /powerbi              # Power BI reports and dashboards
├── /docs                 # Documentation
└── README.md             # This file
```

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
Supervised under the academic framework of Esprit School of Engineering.

This is a comprehensive Business Intelligence project demonstrating modern data engineering practices with complete ETL, orchestration, automation, and visualization workflows.