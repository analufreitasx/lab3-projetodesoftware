import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

import { API_BASE_URL } from '../config/api.config';
import {
  DadosCadastroAluno,
  DadosCadastroEmpresa,
  DadosLogin,
  LoginResponse,
} from '../models/auth.models';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly apiUrl = `${API_BASE_URL}/auth`;

  constructor(private readonly httpClient: HttpClient) {}

  fazerLogin(dadosLogin: DadosLogin): Observable<LoginResponse> {
    return this.httpClient
      .post<LoginResponse>(`${this.apiUrl}/login`, {
        login: dadosLogin.email,
        senha: dadosLogin.senha,
      })
      .pipe(
        tap((resposta) => {
          localStorage.setItem('access_token', resposta.accessToken);
          localStorage.setItem('token_type', resposta.tokenType);
          localStorage.setItem('perfil', resposta.perfil);
        }),
      );
  }

  cadastrarAluno(dadosCadastroAluno: DadosCadastroAluno): void {
    console.log(dadosCadastroAluno);
  }

  cadastrarEmpresa(dadosCadastroEmpresa: DadosCadastroEmpresa): void {
    console.log(dadosCadastroEmpresa);
  }
}
