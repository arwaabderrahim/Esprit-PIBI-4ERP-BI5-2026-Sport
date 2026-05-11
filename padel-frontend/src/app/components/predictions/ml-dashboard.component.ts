import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService, User } from '../../services/auth.service';
import { ApiService, ClassificationResult, RegressionResult, TimeSeriesResult, ClusteringResult } from '../../services/api.service';
import { Subscription } from 'rxjs';
import { RouterLink } from '@angular/router';

interface KpiCard {
  label: string; value: string; delta: string;
  positive: boolean; icon: string; sub: string;
}

@Component({
  selector: 'app-ml-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  template: `
    <div class="predictions-page">

      <!-- Header -->
      <header class="page-header animate-fadeInUp">
        <div class="header-left">
          <h1 class="page-title">{{ roleTitle }}</h1>
          <p class="page-sub">{{ roleSubtitle }}</p>
        </div>
        <div class="header-right">
          <div class="badge" [class.badge-success]="isBackendLive" [class.badge-danger]="!isBackendLive">
            <span class="status-dot" [class.pulse]="isBackendLive"></span>
            {{ isBackendLive ? 'Moteur ML Actif' : 'Moteur ML Deconnecte' }}
          </div>
        </div>
      </header>

      <!-- KPI Grid -->
      <div class="kpi-grid">
        <div *ngFor="let kpi of kpis; let i = index"
             class="kpi-card glass-card animate-fadeInUp"
             [style.animation-delay]="(i * 0.1) + 's'">
          <div class="kpi-icon">{{ kpi.icon }}</div>
          <div class="kpi-content">
            <div class="kpi-label">{{ kpi.label }}</div>
            <div class="kpi-value">{{ kpi.value }}</div>
            <div class="kpi-footer" [class.up]="kpi.positive">{{ kpi.delta }}</div>
          </div>
        </div>
      </div>

      <div class="main-grid">
        <!-- Simulator Section -->
        <div class="panel glass-card animate-fadeInUp" style="animation-delay: 0.3s">
          <div class="panel-header">
            <h2 class="section-title">Inference Predictive ({{ roleModelName }})</h2>
            <div class="badge badge-primary">Simulation temps reel</div>
          </div>

          <div class="prediction-form">

            <!-- ══════════════════════════════════════════════
                 ANALYST FORM — Classification (16 fields)
            ═════════════════════════════════════════════════ -->
            <ng-container *ngIf="user?.role === 'analyst'">
              <div class="pred-grid">
                <div class="team-column">
                  <div class="team-label">🎾 Joueur A</div>
                  <div class="form-row">
                    <label>Points</label>
                    <input type="number" [(ngModel)]="analystForm.Pts_A" class="form-input" placeholder="ex: 3500">
                  </div>
                  <div class="form-row">
                    <label>Position (classement)</label>
                    <input type="number" [(ngModel)]="analystForm.Pos_A" class="form-input" placeholder="ex: 5">
                  </div>
                  <div class="form-row">
                    <label>Momentum (Move)</label>
                    <input type="number" [(ngModel)]="analystForm.Move_A" class="form-input" placeholder="ex: 2 (positif = hausse)">
                  </div>
                  <div class="form-row">
                    <label>Niveau</label>
                    <select [(ngModel)]="analystForm.Level_A" class="form-input">
                      <option value="P1">P1</option>
                      <option value="P2">P2</option>
                      <option value="P3">P3</option>
                      <option value="P4">P4</option>
                      <option value="P5">P5</option>
                      <option value="Premier Padel">Premier Padel</option>
                    </select>
                  </div>
                </div>

                <div class="vs-divider">VS</div>

                <div class="team-column">
                  <div class="team-label">🎾 Joueur B</div>
                  <div class="form-row">
                    <label>Points</label>
                    <input type="number" [(ngModel)]="analystForm.Pts_B" class="form-input" placeholder="ex: 2800">
                  </div>
                  <div class="form-row">
                    <label>Position (classement)</label>
                    <input type="number" [(ngModel)]="analystForm.Pos_B" class="form-input" placeholder="ex: 12">
                  </div>
                  <div class="form-row">
                    <label>Momentum (Move)</label>
                    <input type="number" [(ngModel)]="analystForm.Move_B" class="form-input" placeholder="ex: -1 (baisse)">
                  </div>
                  <div class="form-row">
                    <label>Niveau</label>
                    <select [(ngModel)]="analystForm.Level_B" class="form-input">
                      <option value="P1">P1</option>
                      <option value="P2">P2</option>
                      <option value="P3">P3</option>
                      <option value="P4">P4</option>
                      <option value="P5">P5</option>
                      <option value="Premier Padel">Premier Padel</option>
                    </select>
                  </div>
                </div>
              </div>

              <div class="section-divider">Contexte du Match</div>
              <div class="pred-meta-row">
                <div class="form-row">
                  <label>Écart classement (rank_diff)</label>
                  <input type="number" [(ngModel)]="analystForm.rank_diff" class="form-input" placeholder="Calculé auto si vide">
                </div>
                <div class="form-row">
                  <label>Écart points (pts_diff)</label>
                  <input type="number" [(ngModel)]="analystForm.pts_diff" class="form-input" placeholder="Calculé auto si vide">
                </div>
                <div class="form-row">
                  <label>Année</label>
                  <input type="number" [(ngModel)]="analystForm.Year" class="form-input" placeholder="ex: 2026">
                </div>
                <div class="form-row">
                  <label>Mois (1–12)</label>
                  <input type="number" [(ngModel)]="analystForm.Month" class="form-input" min="1" max="12">
                </div>
                <div class="form-row">
                  <label>Trimestre (1–4)</label>
                  <input type="number" [(ngModel)]="analystForm.Quarter" class="form-input" min="1" max="4">
                </div>
                <div class="form-row">
                  <label>Week-end ?</label>
                  <select [(ngModel)]="analystForm.IsWeekend" class="form-input">
                    <option [ngValue]="0">Non (semaine)</option>
                    <option [ngValue]="1">Oui (week-end)</option>
                  </select>
                </div>
                <div class="form-row">
                  <label>Genre</label>
                  <select [(ngModel)]="analystForm.Gender" class="form-input">
                    <option value="M">Masculin</option>
                    <option value="F">Féminin</option>
                    <option value="Mixed">Mixte</option>
                  </select>
                </div>
                <div class="form-row">
                  <label>Type de match</label>
                  <select [(ngModel)]="analystForm.Match_type" class="form-input">
                    <option value="singles">Singles</option>
                    <option value="doubles">Doubles</option>
                  </select>
                </div>
              </div>
            </ng-container>

            <!-- ══════════════════════════════════════════════
                 FEDERATION FORM — Time Series
            ═════════════════════════════════════════════════ -->
            <ng-container *ngIf="user?.role === 'federation'">
              <div class="form-row">
                <label>Période de prédiction (Mois)</label>
                <input type="number" [(ngModel)]="fedForm.periods" class="form-input" min="1" max="24" placeholder="ex: 6">
                <p class="form-help">Estimer l'évolution des licences sur les prochains mois (1 à 24).</p>
              </div>
            </ng-container>

            <!-- ══════════════════════════════════════════════
                 SPONSOR FORM — Clustering Joueurs (9 fields)
            ═════════════════════════════════════════════════ -->
            <ng-container *ngIf="user?.role === 'sponsor'">
              <div class="section-divider">Profil du Joueur à Segmenter</div>
              <div class="pred-meta-row">
                <div class="form-row">
                  <label>Points</label>
                  <input type="number" [(ngModel)]="sponsorForm.Points" class="form-input" placeholder="ex: 3500">
                </div>
                <div class="form-row">
                  <label>Position (classement)</label>
                  <input type="number" [(ngModel)]="sponsorForm.Position" class="form-input" placeholder="ex: 5">
                </div>
                <div class="form-row">
                  <label>Momentum (Move)</label>
                  <input type="number" [(ngModel)]="sponsorForm.Move" class="form-input" placeholder="ex: 2">
                </div>
                <div class="form-row">
                  <label>Total Victoires</label>
                  <input type="number" [(ngModel)]="sponsorForm.total_wins" class="form-input" placeholder="ex: 45">
                </div>
                <div class="form-row">
                  <label>Total Défaites</label>
                  <input type="number" [(ngModel)]="sponsorForm.total_loss" class="form-input" placeholder="ex: 15">
                </div>
                <div class="form-row">
                  <label>Total Matchs</label>
                  <input type="number" [(ngModel)]="sponsorForm.total_matchs" class="form-input" placeholder="ex: 60">
                </div>
                <div class="form-row">
                  <label>Win Rate (0.0 – 1.0)</label>
                  <input type="number" [(ngModel)]="sponsorForm.win_rate" class="form-input" step="0.01" min="0" max="1" placeholder="ex: 0.75">
                </div>
                <div class="form-row">
                  <label>Niveau</label>
                  <select [(ngModel)]="sponsorForm.Level" class="form-input">
                    <option value="P1">P1</option>
                    <option value="P2">P2</option>
                    <option value="P3">P3</option>
                    <option value="P4">P4</option>
                    <option value="P5">P5</option>
                    <option value="Premier Padel">Premier Padel</option>
                  </select>
                </div>
                <div class="form-row">
                  <label>Genre</label>
                  <select [(ngModel)]="sponsorForm.Genre" class="form-input">
                    <option value="M">Masculin</option>
                    <option value="F">Féminin</option>
                  </select>
                </div>
              </div>
            </ng-container>

            <!-- Submit -->
            <button class="btn-primary predict-btn"
                    (click)="runPrediction()"
                    [disabled]="isProcessing || !isBackendLive">
              <span *ngIf="isProcessing" class="spinner"></span>
              {{ isProcessing ? 'Analyse en cours...' : 'Lancer l\'Analyse ML' }}
            </button>

            <!-- Result -->
            <div *ngIf="predResult" class="pred-result animate-scaleIn">
              <div class="result-label" [innerText]="predResult.title"></div>
              <div class="result-prob" *ngIf="predResult.sub">
                {{ predResult.sub }} : <strong>{{ predResult.value }}</strong>
              </div>
              <div class="result-bar" *ngIf="predResult.percent">
                <div class="result-fill" [style.width]="predResult.percent + '%'"></div>
              </div>
              <p class="result-info" [innerText]="predResult.info"></p>
            </div>

            <!-- Error -->
            <div *ngIf="predError" class="badge badge-danger"
                 style="width:100%; margin-top:20px; padding:15px; display:block;">
              ⚠️ Erreur ML : {{ predError }}
            </div>

          </div>
        </div>

        <!-- Sidebar -->
        <div class="side-column animate-fadeInUp" style="animation-delay: 0.4s">
          <div class="panel glass-card guide-panel">
            <h3 class="side-title">💡 Guide Stratégique</h3>
            <p class="guide-intro">Impact pour votre rôle :</p>
            <ul class="guide-list">
              <li *ngFor="let tip of decisionTips">
                <span class="tip-bullet">✔️</span>
                <span class="tip-text">{{ tip }}</span>
              </li>
            </ul>
            <a routerLink="/dashboard/guide" class="guide-link">Guide complet ➔</a>
          </div>

          <div class="panel glass-card" style="margin-top:25px; background:linear-gradient(135deg, var(--analyst-glow), transparent);">
            <h3 class="side-title">📢 Recommandation</h3>
            <p class="strategy-text">{{ strategicAdvise }}</p>
          </div>
        </div>
      </div>
    </div>
  `,
  styleUrl: './ml-dashboard.component.css'
})
export class MlDashboardComponent implements OnInit, OnDestroy {
  user: User | null = null;
  isBackendLive = false;
  isProcessing = false;
  predResult: any = null;
  predError = '';
  private subs: Subscription[] = [];

  roleTitle = '';
  roleSubtitle = '';
  roleModelName = '';
  kpis: KpiCard[] = [];
  decisionTips: string[] = [];
  strategicAdvise = '';

  // ── ANALYST FORM ────────────────────────────────────────────────
  analystForm = {
    Pts_A: null as number | null,
    Pos_A: null as number | null,
    Move_A: null as number | null,
    Level_A: 'P1',
    Pts_B: null as number | null,
    Pos_B: null as number | null,
    Move_B: null as number | null,
    Level_B: 'P2',
    rank_diff: null as number | null,
    pts_diff: null as number | null,
    Year: new Date().getFullYear(),
    Month: new Date().getMonth() + 1,
    Quarter: Math.ceil((new Date().getMonth() + 1) / 3),
    IsWeekend: 0,
    Gender: 'M',
    Match_type: 'singles'
  };

  // ── FEDERATION FORM ─────────────────────────────────────────────
  fedForm = { periods: 6 };

  // ── SPONSOR FORM — Clustering (9 fields) ────────────────────────
  sponsorForm = {
    Points:       null as number | null,
    Position:     null as number | null,
    Move:         null as number | null,
    total_wins:   null as number | null,
    total_loss:   null as number | null,
    total_matchs: null as number | null,
    win_rate:     null as number | null,
    Level:        'P1',
    Genre:        'M'
  };

  constructor(private auth: AuthService, private api: ApiService) {}

  ngOnInit(): void {
    this.user = this.auth.getUser();
    this.setupRoleContent();
    this.loadHealth();
  }

  ngOnDestroy(): void {
    this.subs.forEach(s => s.unsubscribe());
  }

  private setupRoleContent(): void {
    const role = this.user?.role ?? 'analyst';

    if (role === 'analyst') {
      this.roleTitle = 'Analyse de Performance';
      this.roleSubtitle = 'Optimisation des performances sportives.';
      this.roleModelName = 'Classification XGBoost';
      this.kpis = [
        { label: 'Precision Win',  value: '94.7%', delta: 'Stable',  positive: true, icon: '🎯', sub: 'Win/Loss' },
        { label: 'Confiance Moy.', value: '88%',   delta: '↑ 1.2%', positive: true, icon: '🧠', sub: 'Inference' },
        { label: 'Top Joueurs',    value: '124',    delta: '+5',      positive: true, icon: '👤', sub: 'Analyses' },
        { label: 'Matchs Prevus',  value: '18',     delta: 'Semaine', positive: true, icon: '🎾', sub: 'Calendrier' }
      ];
      this.decisionTips = [
        'Score > 85% = Avantage critique.',
        'Le Momentum (Move_A) est clé.',
        'Rank Diff impacte 40%.'
      ];
      this.strategicAdvise = 'Prévoyez un entraînement spécifique sur le revers pour le Joueur A.';

    } else if (role === 'federation') {
      this.roleTitle = 'Décisions Fédération';
      this.roleSubtitle = 'Pilotage de la croissance du Padel.';
      this.roleModelName = 'Time Series Prophet';
      this.kpis = [
        { label: 'Proj. Licences', value: '+18%', delta: '↑ 3%',  positive: true, icon: '📈', sub: '2026' },
        { label: 'Succès Tournoi', value: '85%',  delta: 'Stable', positive: true, icon: '🏟️', sub: 'ML Prediction' },
        { label: 'Nouveaux Clubs', value: '12',   delta: 'Mois',   positive: true, icon: '🏛️', sub: 'Affiliations' },
        { label: 'Budget Prév.',   value: '2.4M', delta: '+150k',  positive: true, icon: '💰', sub: 'ML Forecast' }
      ];
      this.decisionTips = [
        'Croissance > 10% = Investissement.',
        "Niveau FIP drive l'affluence.",
        'Priorisez les clubs ML-High.'
      ];
      this.strategicAdvise = "La région Sud nécessite 3 nouveaux terrains d'ici Septembre.";

    } else {
      this.roleTitle = 'Stratégie Sponsor';
      this.roleSubtitle = 'Segmentation ML des joueurs pour cibler vos sponsorings.';
      this.roleModelName = 'Clustering Joueurs';
      this.kpis = [
        { label: 'ROI Estimé',  value: '4.2x',  delta: '↑ 0.8x', positive: true, icon: '💰', sub: 'Prediction' },
        { label: 'Visibilité',  value: '92/100', delta: '↑ 5pts', positive: true, icon: '📺', sub: 'Score Media' },
        { label: 'Engagement',  value: 'High',   delta: '+12%',   positive: true, icon: '❤️', sub: 'Sentiment' },
        { label: 'Partenaires', value: '28',     delta: '+3',     positive: true, icon: '🏷️', sub: 'Actifs' }
      ];
      this.decisionTips = [
        'Élite → contrats équipement premium.',
        'Challenger → sponsoring long terme.',
        'Amateur → notoriété de marque.'
      ];
      this.strategicAdvise = "Identifiez le segment ML du joueur pour adapter votre offre sponsoring et maximiser le ROI.";
    }
  }

  loadHealth(): void {
    const s = this.api.getHealth().subscribe({
      next: () => { this.isBackendLive = true; },
      error: () => { this.isBackendLive = false; }
    });
    this.subs.push(s);
  }

  runPrediction(): void {
    this.isProcessing = true;
    this.predResult = null;
    this.predError = '';
    const role = this.user?.role ?? 'analyst';

    if (role === 'analyst') {
      const form = { ...this.analystForm };
      if (form.rank_diff === null && form.Pos_A !== null && form.Pos_B !== null) {
        form.rank_diff = form.Pos_B - form.Pos_A;
      }
      if (form.pts_diff === null && form.Pts_A !== null && form.Pts_B !== null) {
        form.pts_diff = form.Pts_A - form.Pts_B;
      }
      this.api.predictClassification(form as any).subscribe({
        next: (r: ClassificationResult) => {
          this.predResult = {
            title: r.prediction === 1 ? '✅ VICTOIRE PREDITE — Joueur A' : '❌ DEFAITE PREDITE — Joueur A',
            sub: 'Probabilité de succès',
            value: (r.win_probability * 100).toFixed(1) + '%',
            percent: r.win_probability * 100,
            info: `Modèle : ${r.model} | Label : ${r.label}`
          };
          this.isProcessing = false;
        },
        error: (e: Error) => { this.predError = e.message; this.isProcessing = false; }
      });

    } else if (role === 'federation') {
      this.api.predictTimeSeries(this.fedForm.periods).subscribe({
        next: (r: TimeSeriesResult[]) => {
          const last = r[r.length - 1];
          this.predResult = {
            title: '📈 PROJECTION DE CROISSANCE',
            sub: `Licences prévues (horizon ${this.fedForm.periods} mois)`,
            value: Math.round(last.yhat).toLocaleString(),
            info: `Intervalle de confiance : [${Math.round(last.yhat_lower).toLocaleString()} – ${Math.round(last.yhat_upper).toLocaleString()}]`
          };
          this.isProcessing = false;
        },
        error: (e: Error) => { this.predError = e.message; this.isProcessing = false; }
      });

    } else {
      // SPONSOR — Clustering
      this.api.predictClustering(this.sponsorForm as any).subscribe({
        next: (r: ClusteringResult) => {
          const recommendations: Record<string, string> = {
            'Joueur elite (top classement)':
              '🏆 Cible PREMIUM — Visibilité maximale, ROI élevé. Proposez des contrats équipement haut de gamme et logo maillot.',
            'Joueur challenger (classement 100-500)':
              '📈 Cible CROISSANCE — Joueur en forte progression. Idéal pour sponsoring long terme et fidélisation de marque.',
            'Joueur amateur (classement > 500)':
              '🎯 Cible VOLUME — Large audience accessible. Parfait pour notoriété de marque et équipements entrée de gamme.'
          };
          const advice = recommendations[r.profil] ?? 'Analysez ce segment pour définir votre stratégie sponsoring optimale.';
          this.predResult = {
            title: `🎯 SEGMENT DÉTECTÉ : ${r.profil.toUpperCase()}`,
            sub: 'Cluster ML',
            value: `Cluster ${r.cluster}`,
            info: advice
          };
          this.isProcessing = false;
        },
        error: (e: Error) => { this.predError = e.message; this.isProcessing = false; }
      });
    }
  }
}