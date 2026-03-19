import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface DashboardResumo {
  quantidadePessoas: number;
}

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private readonly http = inject(HttpClient);

  carregarResumo(): Observable<DashboardResumo> {
    return this.http.get<DashboardResumo>('/api/v0/dashboard/resumo');
  }
}

