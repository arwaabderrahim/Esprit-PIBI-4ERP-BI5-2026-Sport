FROM python:3.11-slim

WORKDIR /app

# Installation des outils système nécessaires
RUN apt-get update && apt-get install -y \
    build-essential \
    && rm -rf /var/lib/apt/lists/*

# Copie du fichier requirements et installation des packages
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copie du code principal
COPY main.py .

# Copie du dossier des modèles
COPY models/ ./models/

# Exposition du port utilisé par FastAPI
EXPOSE 8000

# Commande pour lancer l'API quand le conteneur démarre
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]