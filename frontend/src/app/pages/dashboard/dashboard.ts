import { Component, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';

import { DashboardService, DashboardResumo } from '../../core/dashboard.service';
import { AuthService } from '../../core/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class DashboardComponent implements OnInit {
  private readonly dashboardService = inject(DashboardService);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  resumo?: DashboardResumo;
  loading = true;
  errorMessage = '';

  ngOnInit(): void {
    this.dashboardService.carregarResumo().subscribe({
      next: (resumo) => {
        this.resumo = resumo;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Não foi possível carregar o dashboard.';
        this.loading = false;
      },
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
