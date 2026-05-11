import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService, User } from '../../services/auth.service';

@Component({
  selector: 'app-guide',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="guide-page animate-fadeInUp">
      <header class="page-header">
        <h1 class="page-title">Guide d'Aide à la Décision</h1>
        <p class="page-sub">Comprendre et exploiter les prédictions ML pour votre rôle : {{ user?.role }}</p>
      </header>

      <div class="guide-content glass-card">
        <!-- Role Specific Sections -->
        <div *ngIf="user?.role === 'analyst'" class="role-guide">
          <h2>🎯 Guide de l'Analyste Sportif</h2>
          <section>
            <h3>1. Interprétation des Scores</h3>
            <p>La probabilité de victoire est calculée via un modèle XGBoost entraîné sur 10 ans de données WPT. Un score supérieur à 85% est considéré comme une prédiction "Forte".</p>
          </section>
          <section>
            <h3>2. Facteurs de Performance</h3>
            <ul>
              <li><strong>Rank Diff :</strong> L'écart de classement reste le prédicteur #1.</li>
              <li><strong>Momentum :</strong> La progression récente (Move_A) influence la confiance du modèle.</li>
            </ul>
          </section>
        </div>

        <div *ngIf="user?.role === 'federation'" class="role-guide">
          <h2>🏟️ Guide du Décideur Fédération</h2>
          <section>
            <h3>1. Pilotage de la Croissance</h3>
            <p>Utilisez les projections de licences pour allouer vos budgets régionaux. Les zones en vert sombre sur vos rapports BI indiquent un potentiel de saturation à surveiller.</p>
          </section>
          <section>
            <h3>2. Succès des Tournois</h3>
            <p>Le modèle prédit l'attractivité d'un tournoi selon son niveau (FIP Platinum vs Gold). Un score de 90/100 suggère une billetterie complète.</p>
          </section>
        </div>

        <div *ngIf="user?.role === 'sponsor'" class="role-guide">
          <h2>💰 Guide de l'Investisseur Sponsor</h2>
          <section>
            <h3>1. Mesure du ROI</h3>
            <p>Le ROI prédit n'est pas seulement financier, il inclut l'équivalent publicitaire (Media Value). Un ROI de 4x signifie que chaque euro investi génère 4 euros de visibilité.</p>
          </section>
          <section>
            <h3>2. Sélection des Talents</h3>
            <p>Le modèle d'engagement analyse le sentiment des fans sur les réseaux sociaux. C'est un indicateur clé avant de signer un nouveau contrat de sponsoring.</p>
          </section>
        </div>

        <div class="general-tips">
          <h3>💡 Conseils Généraux</h3>
          <p>Les modèles ML fournissent une aide à la décision, mais ne remplacent pas l'expertise humaine. Utilisez-les pour valider vos intuitions ou détecter des anomalies.</p>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .guide-page { padding: 30px; max-width: 1000px; margin: 0 auto; }
    .page-header { margin-bottom: 30px; }
    .page-title { font-size: 2.2rem; margin-bottom: 10px; color: var(--text-primary); }
    .guide-content { padding: 40px; }
    .role-guide h2 { color: var(--primary); margin-bottom: 25px; border-bottom: 2px solid var(--primary-glow); padding-bottom: 10px; }
    section { margin-bottom: 30px; }
    h3 { margin-bottom: 15px; color: var(--text-primary); }
    ul { padding-left: 20px; }
    li { margin-bottom: 10px; }
    .general-tips { margin-top: 50px; padding: 20px; background: var(--bg-base); border-radius: 12px; border-left: 5px solid var(--accent); }
  `]
})
export class GuideComponent implements OnInit {
  user: User | null = null;
  constructor(private auth: AuthService) {}
  ngOnInit(): void {
    this.user = this.auth.getUser();
  }
}
