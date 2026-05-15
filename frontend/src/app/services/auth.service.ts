import { computed, inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable, tap } from 'rxjs';

import { API_BASE_URL } from '../config/api.config';
import {
  DadosCadastroAluno,
  DadosCadastroEmpresa,
  DadosLogin,
  LoginResponse,
  PerfilUsuario,
} from '../models/auth.models';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly httpClient = inject(HttpClient);
  private readonly tokenAcessoSignal = signal<string | null>(localStorage.getItem('access_token'));
  private readonly perfilAtualSignal = signal<PerfilUsuario | null>(this.lerPerfilArmazenado());

  readonly tokenAcesso = this.tokenAcessoSignal.asReadonly();
  readonly perfilAtual = this.perfilAtualSignal.asReadonly();
  readonly estaAutenticado = computed(() => Boolean(this.tokenAcesso() && this.perfilAtual()));

  fazerLogin(dadosLogin: DadosLogin): Observable<LoginResponse> {
    return this.httpClient
      .post<LoginResponse>(`${API_BASE_URL}/auth/login`, {
        email: dadosLogin.email,
        senha: dadosLogin.senha,
      })
      .pipe(
        map((resposta) => {
          const perfilNormalizado = this.normalizarPerfil(resposta.perfil);

          if (!perfilNormalizado) {
            throw new Error('Perfil de usuário inválido.');
          }

          return { ...resposta, perfil: perfilNormalizado };
        }),
        tap((resposta) => {
          localStorage.setItem('access_token', resposta.accessToken);
          localStorage.setItem('token_type', resposta.tokenType);
          localStorage.setItem('perfil', resposta.perfil);
          this.tokenAcessoSignal.set(resposta.accessToken);
          this.perfilAtualSignal.set(resposta.perfil);
        }),
      );
  }

  sair(): void {
    localStorage.removeItem('access_token');
    localStorage.removeItem('token_type');
    localStorage.removeItem('perfil');
    this.tokenAcessoSignal.set(null);
    this.perfilAtualSignal.set(null);
  }

  obterTokenAcesso(): string | null {
    return this.tokenAcesso();
  }

  usuarioTemPerfil(perfil: PerfilUsuario): boolean {
    return this.estaAutenticado() && this.perfilAtual() === perfil;
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
      endereco: {
        cep: dadosCadastroAluno.endereco.cep,
        rua: dadosCadastroAluno.endereco.rua,
        numero: dadosCadastroAluno.endereco.numero,
        complemento: dadosCadastroAluno.endereco.complemento,
        bairro: dadosCadastroAluno.endereco.bairro,
        cidade: dadosCadastroAluno.endereco.cidade,
        uf: dadosCadastroAluno.endereco.uf,
      },
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

  private lerPerfilArmazenado(): PerfilUsuario | null {
    const perfil = localStorage.getItem('perfil');

    return this.normalizarPerfil(perfil);
  }

  private normalizarPerfil(perfil: string | null): PerfilUsuario | null {
    const perfilNormalizado = perfil?.trim().toUpperCase().replace('ROLE_', '');

    if (
      perfilNormalizado === 'ALUNO' ||
      perfilNormalizado === 'PROFESSOR' ||
      perfilNormalizado === 'EMPRESA'
    ) {
      return perfilNormalizado;
    }

    return null;
  }
}
