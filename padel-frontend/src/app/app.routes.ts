import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';
import { LoginComponent } from './components/login/login.component';
import { DashboardShellComponent } from './components/dashboard-shell/dashboard-shell.component';
import { MlDashboardComponent } from './components/predictions/ml-dashboard.component';
import { ReportsComponent } from './components/reports/reports.component';
import { GuideComponent } from './components/guide/guide.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'dashboard',
    component: DashboardShellComponent,
    canActivate: [authGuard],
    children: [
      { path: 'predictions', component: MlDashboardComponent },
      { path: 'reports', component: ReportsComponent },
      { path: 'guide', component: GuideComponent },
      { path: '', redirectTo: 'predictions', pathMatch: 'full' }
    ]
  },
  { path: '**', redirectTo: 'login' }
];
