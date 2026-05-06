import { ChangeDetectionStrategy, Component, computed, inject, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { ModoAutenticacao } from '../../models/auth.models';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-auth',
  imports: [ReactiveFormsModule],
  templateUrl: './auth.html',
  styleUrl: './auth.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AuthPage {
  private readonly authService = inject(AuthService);

  protected readonly modoAtual = signal<ModoAutenticacao>('login');
  protected readonly formularioEnviado = signal(false);
  protected readonly menuCadastroAberto = signal(false);
  protected readonly loginCarregando = signal(false);
  protected readonly loginErro = signal<string | null>(null);
  protected readonly loginSucesso = signal<string | null>(null);

  protected readonly estaEmModoCadastro = computed(() => this.modoAtual() !== 'login');

  protected readonly titulo = computed(() => {
    switch (this.modoAtual()) {
      case 'cadastro-aluno':
        return 'Cadastro de aluno';
      case 'cadastro-empresa':
        return 'Cadastro de empresa';
      default:
        return 'Bem-vindo de volta';
    }
  });

  protected readonly subtitulo = computed(() => {
    switch (this.modoAtual()) {
      case 'cadastro-aluno':
        return 'Informe seus dados acadêmicos para começar a acumular moedas.';
      case 'cadastro-empresa':
        return 'Cadastre sua empresa parceira para oferecer vantagens aos estudantes.';
      default:
        return 'Acesse sua conta para acompanhar moedas, vantagens e transações do ecossistema estudantil.';
    }
  });

  protected readonly formularioLogin = new FormGroup({
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
    senha: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(6)],
    }),
  });

  protected readonly formularioAluno = new FormGroup({
    nome: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    cpf: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    rg: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    endereco: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    instituicao: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    curso: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
    senha: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(6)],
    }),
  });

  protected readonly formularioEmpresa = new FormGroup({
    nome: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
    senha: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(6)],
    }),
  });

  protected selecionarLogin(): void {
    this.modoAtual.set('login');
    this.menuCadastroAberto.set(false);
    this.formularioEnviado.set(false);
  }

  protected alternarMenuCadastro(): void {
    this.menuCadastroAberto.update((estaAberto) => !estaAberto);
  }

  protected selecionarModoCadastro(modo: Exclude<ModoAutenticacao, 'login'>): void {
    this.modoAtual.set(modo);
    this.menuCadastroAberto.set(false);
    this.formularioEnviado.set(false);
  }

  protected enviarLogin(): void {
    this.formularioEnviado.set(true);
    this.loginErro.set(null);
    this.loginSucesso.set(null);

    if (this.formularioLogin.invalid) {
      this.formularioLogin.markAllAsTouched();
      return;
    }

    this.loginCarregando.set(true);
    this.authService.fazerLogin(this.formularioLogin.getRawValue()).subscribe({
      next: (resposta) => {
        this.loginCarregando.set(false);
        this.loginSucesso.set(`Login realizado com sucesso (${resposta.perfil}).`);
      },
      error: (erro: HttpErrorResponse) => {
        this.loginCarregando.set(false);
        if (erro.status === 401) {
          this.loginErro.set('Credenciais inválidas. Verifique email e senha.');
          return;
        }
        this.loginErro.set('Não foi possível entrar agora. Tente novamente.');
      },
    });
  }

  protected enviarCadastroAluno(): void {
    this.formularioEnviado.set(true);
    this.loginErro.set(null);
    this.loginSucesso.set(null);

    if (this.formularioAluno.invalid) {
      this.formularioAluno.markAllAsTouched();
      return;
    }

    this.loginCarregando.set(true);
    this.authService.cadastrarAluno(this.formularioAluno.getRawValue()).subscribe({
      next: () => {
        this.loginCarregando.set(false);
        this.loginSucesso.set('Cadastro realizado com sucesso! Faça login para continuar.');
        this.formularioAluno.reset();
        this.modoAtual.set('login');
      },
      error: (erro: HttpErrorResponse) => {
        this.loginCarregando.set(false);
        this.loginErro.set(
          erro?.error?.detail ?? 'Não foi possível realizar o cadastro. Tente novamente.',
        );
      },
    });
  }

  protected enviarCadastroEmpresa(): void {
    this.formularioEnviado.set(true);

    if (this.formularioEmpresa.invalid) {
      this.formularioEmpresa.markAllAsTouched();
      return;
    }

    this.authService.cadastrarEmpresa(this.formularioEmpresa.getRawValue());
  }

  protected deveMostrarErro(controle: FormControl<string>): boolean {
    return controle.invalid && (controle.touched || this.formularioEnviado());
  }
}
