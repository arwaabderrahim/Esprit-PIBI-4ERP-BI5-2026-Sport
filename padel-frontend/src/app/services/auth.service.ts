import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

export type UserRole = 'analyst' | 'federation' | 'sponsor';

export interface User {
  id: string;
  name: string;
  email: string;
  role: UserRole;
  avatar: string;
  organization: string;
  lastLogin: Date;
}

const MOCK_USERS: Record<string, User> = {
  'analyst': {
    id: 'usr-001',
    name: 'Mehdi Bouazza',
    email: 'analyst@padel-analytics.com',
    role: 'analyst',
    avatar: 'MB',
    organization: 'Sport Analytics Lab',
    lastLogin: new Date()
  },
  'federation': {
    id: 'usr-002',
    name: 'Camille Dupont',
    email: 'federation@rfpadel.fr',
    role: 'federation',
    avatar: 'CD',
    organization: 'Fédération Française de Padel',
    lastLogin: new Date()
  },
  'sponsor': {
    id: 'usr-003',
    name: 'Arwa Abderrahim',
    email: 'sponsor@bullpadel.com',
    role: 'sponsor',
    avatar: 'AA',
    organization: 'Bull Padel',
    lastLogin: new Date()
  }
};

@Injectable({ providedIn: 'root' })
export class AuthService {
  private currentUser: User | null = null;

  constructor(private router: Router) {
    const stored = sessionStorage.getItem('padel_user');
    if (stored) {
      this.currentUser = JSON.parse(stored);
    }
  }

  login(role: UserRole): boolean {
    const user = MOCK_USERS[role];
    if (user) {
      this.currentUser = { ...user, lastLogin: new Date() };
      sessionStorage.setItem('padel_user', JSON.stringify(this.currentUser));
      return true;
    }
    return false;
  }

  logout(): void {
    this.currentUser = null;
    sessionStorage.removeItem('padel_user');
    this.router.navigate(['/login']);
  }

  getUser(): User | null {
    return this.currentUser;
  }

  isAuthenticated(): boolean {
    return this.currentUser !== null;
  }

  getRole(): UserRole | null {
    return this.currentUser?.role ?? null;
  }
}
