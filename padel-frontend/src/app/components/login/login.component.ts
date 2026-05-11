import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService, UserRole } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  loginData = {
    email: '',
    password: ''
  };
  
  isLoading = false;
  loginError = '';
  currentYear = new Date().getFullYear();

  // Valid credentials mapping
  private validUsers: Record<string, { role: UserRole, pass: string }> = {
    'analyst@padel.com': { role: 'analyst', pass: 'Padel2026!' },
    'federation@padel.com': { role: 'federation', pass: 'Padel2026!' },
    'sponsor@padel.com': { role: 'sponsor', pass: 'Padel2026!' }
  };

  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {
    if (this.auth.isAuthenticated()) {
      this.router.navigate(['/dashboard/predictions']);
    }
  }

  submitLogin(): void {
    this.loginError = '';
    const user = this.validUsers[this.loginData.email];
    
    if (user && user.pass === this.loginData.password) {
      this.isLoading = true;
      
      // Simulate network delay
      setTimeout(() => {
        this.isLoading = false;
        // Log in with the role associated to the email
        this.auth.login(user.role);
        // Direct navigation to the dashboard
        this.router.navigate(['/dashboard/predictions']);
      }, 1200);
    } else {
      this.loginError = 'Identifiants Microsoft invalides. Veuillez réessayer.';
    }
  }
}
