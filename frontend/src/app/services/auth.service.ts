import { Injectable } from '@angular/core';

import { DadosCadastroAluno, DadosCadastroEmpresa, DadosLogin } from '../models/auth.models';

@Injectable({ providedIn: 'root' })
export class AuthService {
  fazerLogin(dadosLogin: DadosLogin): void {
    console.log(dadosLogin);
  }

  cadastrarAluno(dadosCadastroAluno: DadosCadastroAluno): void {
    console.log(dadosCadastroAluno);
  }

  cadastrarEmpresa(dadosCadastroEmpresa: DadosCadastroEmpresa): void {
    console.log(dadosCadastroEmpresa);
  }
}
