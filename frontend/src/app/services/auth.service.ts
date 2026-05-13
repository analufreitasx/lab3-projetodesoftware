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

  constructor(private readonly httpClient: HttpClient) {}

  fazerLogin(dadosLogin: DadosLogin): Observable<LoginResponse> {
    return this.httpClient
      .post<LoginResponse>(`${API_BASE_URL}/auth/login`, {
        email: dadosLogin.email,
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

  cadastrarAluno(dadosCadastroAluno: DadosCadastroAluno): Observable<unknown> {
    return this.httpClient.post(`${API_BASE_URL}/alunos`, {
      nome: dadosCadastroAluno.nome,
      senha: dadosCadastroAluno.senha,
      email: dadosCadastroAluno.email,
      cpf: dadosCadastroAluno.cpf,
      rg: dadosCadastroAluno.rg,
      curso: dadosCadastroAluno.curso,
      instituicao: dadosCadastroAluno.instituicao,
      endereco: dadosCadastroAluno.endereco,
    });
  }

  cadastrarEmpresa(dadosCadastroEmpresa: DadosCadastroEmpresa): Observable<unknown> {
    return this.httpClient.post(`${API_BASE_URL}/empresas`, {
      nome: dadosCadastroEmpresa.nome,
      senha: dadosCadastroEmpresa.senha,
      email: dadosCadastroEmpresa.email,
      cnpj: dadosCadastroEmpresa.cnpj,
    });
  }
}
