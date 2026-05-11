import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService, User } from '../../services/auth.service';

@Component({
  selector: 'app-dashboard-shell',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './dashboard-shell.component.html',
  styleUrl: './dashboard-shell.component.css'
})
export class DashboardShellComponent implements OnInit {
  user: User | null = null;
  sidebarOpen = true;
  currentTime = new Date();

  navItems = [
    { label: 'Prédictions ML', icon: '🤖', route: './predictions', exact: true },
    { label: 'Rapports BI', icon: '📊', route: './reports', exact: false },
    { label: 'Guide Décision', icon: '💡', route: './guide', exact: false }
  ];

  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.user = this.auth.getUser();
    setInterval(() => { this.currentTime = new Date(); }, 1000);
  }

  logout(): void {
    this.auth.logout();
  }

  toggleSidebar(): void {
    this.sidebarOpen = !this.sidebarOpen;
  }

  getRoleLabel(): string {
    const labels: Record<string, string> = {
      analyst: 'Sport Analyst',
      federation: 'Fédération Padel',
      sponsor: 'Sponsor'
    };
    return labels[this.user?.role ?? ''] ?? '';
  }

  getRoleColor(): string {
    const colors: Record<string, string> = {
      analyst: '#ec4899', // Pink
      federation: '#10b981', // Green
      sponsor: '#f59e0b' // Orange
    };
    return colors[this.user?.role ?? ''] ?? '#ec4899';
  }
}
