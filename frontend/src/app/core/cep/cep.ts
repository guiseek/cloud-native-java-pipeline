import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, map } from 'rxjs';

interface ViaCepResponse {
  cep?: string;
  logradouro?: string;
  bairro?: string;
  localidade?: string;
  uf?: string;
  erro?: boolean;
}

export interface CepLookupResult {
  cep: string;
  endereco: string;
  bairro: string;
  cidade: string;
  uf: string;
}

@Injectable({
  providedIn: 'root',
})
export class CepService {
  private readonly http = inject(HttpClient);

  buscarEndereco(cep: string): Observable<CepLookupResult | null> {
    const sanitizedCep = cep.replace(/\D/g, '');

    return this.http.get<ViaCepResponse>(`https://viacep.com.br/ws/${sanitizedCep}/json/`).pipe(
      map((response) => {
        if (response.erro) {
          return null;
        }

        return {
          cep: response.cep?.replace(/\D/g, '') ?? sanitizedCep,
          endereco: response.logradouro?.trim() ?? '',
          bairro: response.bairro?.trim() ?? '',
          cidade: response.localidade?.trim() ?? '',
          uf: response.uf?.trim().toUpperCase() ?? '',
        };
      }),
    );
  }
}
