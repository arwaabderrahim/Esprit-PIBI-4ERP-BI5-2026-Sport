import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { AuthService, User } from '../../services/auth.service';

@Component({
  selector: 'app-reports',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reports.component.html',
  styleUrl: './reports.component.css'
})
export class ReportsComponent implements OnInit {
  user: User | null = null;
  reportUrl: SafeResourceUrl | null = null;
  
  // Mapping of roles to their REAL internal IDs provided by the user
  private rolePages: Record<string, string> = {
    'federation': 'ReportSection65c01c15f716250b7b0b',
    'sponsor': 'ReportSectiona8b70697f42f7cf71020',
    'analyst': 'ReportSection734a0a24256d016f69cb',
    'index': 'ReportSectiona757165d322e88d2828d'
  };

  constructor(private auth: AuthService, private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
    this.user = this.auth.getUser();
    this.updateReportUrl();
  }

  updateReportUrl(): void {
    const reportId = 'cc9ba001-7888-45e6-bd69-d13d5f00d8ee';
    const role = this.user?.role ?? 'analyst';
    const pageId = this.rolePages[role] || this.rolePages['index'];

    // chromeless=1 : Hides the entire Power BI chrome (top and bottom bars)
    // navContentPaneEnabled=false : Specifically hides the page tabs
    // filterPaneEnabled=false : Hides the filters pane on the right
    let fullUrl = `https://app.powerbi.com/reportEmbed?reportId=${reportId}&autoAuth=true&chromeless=1&navContentPaneEnabled=false&filterPaneEnabled=false`;
    
    if (pageId) {
      fullUrl += `&pageName=${pageId}`;
    }
    
    this.reportUrl = this.sanitizer.bypassSecurityTrustResourceUrl(fullUrl);
  }
  
  getRoleLabel(): string {
    const labels: Record<string, string> = {
      analyst: 'Sport Analyst',
      federation: 'Fédération Padel',
      sponsor: 'Sponsor'
    };
    return labels[this.user?.role ?? ''] ?? 'Rapport BI';
  }
}
