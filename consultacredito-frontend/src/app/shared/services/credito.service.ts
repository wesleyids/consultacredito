import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Credito } from '../models/credito.model';

@Injectable({
    providedIn: 'root',
})
export class CreditoService {
    baseUrl = environment.apiUrl;

    constructor(
        private http: HttpClient
    ) { }

    buscarPorNumeroNfse(numeroNfse: string) {
        return this.http
            .get<Credito[]>(`${this.baseUrl}/${numeroNfse}`)
            .pipe(
                map((response: Credito[]) => {
                    return response;
                })
            );
    }

    buscarPorNumeroCredito(numeroCredito: string) {
        return this.http
            .get<Credito>(`${this.baseUrl}/credito/${numeroCredito}`)
            .pipe(
                map((response: Credito) => {
                    return response;
                })
            );
    }
}