import { ChangeDetectionStrategy, Component, computed, inject, signal } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { ModoAutenticacao } from '../../models/auth.models';
import { AuthService } from '../../services/auth.service';

const REGEX_SENHA_FORTE = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^a-zA-Z\d]).{6,}$/;

interface RespostaViaCep {
  cep?: string;
  logradouro?: string;
  bairro?: string;
  localidade?: string;
  uf?: string;
  erro?: boolean | string;
}

@Component({
  selector: 'app-auth',
  imports: [ReactiveFormsModule],
  templateUrl: './auth.html',
  styleUrl: './auth.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AuthPage {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);
  private readonly httpClient = inject(HttpClient);

  protected readonly modoAtual = signal<ModoAutenticacao>('login');
  protected readonly formularioEnviado = signal(false);
  protected readonly menuCadastroAberto = signal(false);
  protected readonly loginCarregando = signal(false);
  protected readonly loginErro = signal<string | null>(null);
  protected readonly loginSucesso = signal<string | null>(null);
  protected readonly enderecoExpandido = signal(false);
  protected readonly buscandoCep = signal(false);
  protected readonly mostrarSenhaLogin = signal(false);
  protected readonly mostrarSenhaAluno = signal(false);
  protected readonly mostrarConfirmarSenhaAluno = signal(false);
  protected readonly mostrarSenhaEmpresa = signal(false);
  protected readonly mostrarConfirmarSenhaEmpresa = signal(false);

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
    endereco: new FormGroup({
      cep: new FormControl('', {
        nonNullable: true,
        validators: [Validators.minLength(8), Validators.maxLength(9)],
      }),
      rua: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
      numero: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
      complemento: new FormControl('', { nonNullable: true }),
      bairro: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
      cidade: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
      uf: new FormControl('', {
        nonNullable: true,
        validators: [Validators.required, Validators.minLength(2), Validators.maxLength(2)],
      }),
    }),
    instituicao: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    curso: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
    senha: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.pattern(REGEX_SENHA_FORTE)],
    }),
    confirmarSenha: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required],
    }),
  }, { validators: senhasIguais });

  protected readonly formularioEmpresa = new FormGroup({
    nome: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
    senha: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.pattern(REGEX_SENHA_FORTE)],
    }),
    confirmarSenha: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required],
    }),
    cnpj: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(14), Validators.maxLength(18)],
    }),
  }, { validators: senhasIguais });

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

  protected aoDigitarCep(evento: Event): void {
    const valor = (evento.target as HTMLInputElement).value;
    const apenasDigitos = valor.replace(/\D/g, '');

    if (apenasDigitos.length !== 8) {
      return;
    }

    this.enderecoExpandido.set(true);
    this.buscarCepNoViaCep(apenasDigitos);
  }

  protected naoSeiCep(): void {
    this.enderecoExpandido.set(true);
  }

  private buscarCepNoViaCep(cep: string): void {
    this.buscandoCep.set(true);
    this.httpClient
      .get<RespostaViaCep>(`https://viacep.com.br/ws/${cep}/json/`)
      .subscribe({
        next: (resposta) => {
          this.buscandoCep.set(false);
          if (resposta.erro) {
            return;
          }
          this.preencherEnderecoSeVazio(resposta);
        },
        error: () => {
          this.buscandoCep.set(false);
        },
      });
  }

  private preencherEnderecoSeVazio(resposta: RespostaViaCep): void {
    const endereco = this.formularioAluno.controls.endereco.controls;

    if (!endereco.rua.value && resposta.logradouro) {
      endereco.rua.setValue(resposta.logradouro);
    }
    if (!endereco.bairro.value && resposta.bairro) {
      endereco.bairro.setValue(resposta.bairro);
    }
    if (!endereco.cidade.value && resposta.localidade) {
      endereco.cidade.setValue(resposta.localidade);
    }
    if (!endereco.uf.value && resposta.uf) {
      endereco.uf.setValue(resposta.uf);
    }
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
      next: () => {
        this.loginCarregando.set(false);
        void this.router.navigateByUrl('/home', { replaceUrl: true });
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
      this.enderecoExpandido.set(true);
      return;
    }

    this.loginCarregando.set(true);
    const { confirmarSenha: _confirmarSenhaAluno, ...dadosAluno } =
      this.formularioAluno.getRawValue();
    this.authService.cadastrarAluno(dadosAluno).subscribe({
      next: () => {
        this.loginCarregando.set(false);
        this.loginSucesso.set('Cadastro realizado com sucesso! Faça login para continuar.');
        this.formularioAluno.reset();
        this.formularioEnviado.set(false);
        this.enderecoExpandido.set(false);
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
    this.loginErro.set(null);
    this.loginSucesso.set(null);

    if (this.formularioEmpresa.invalid) {
      this.formularioEmpresa.markAllAsTouched();
      return;
    }

    this.loginCarregando.set(true);
    const { confirmarSenha: _confirmarSenhaEmpresa, ...dadosEmpresa } =
      this.formularioEmpresa.getRawValue();
    this.authService.cadastrarEmpresa(dadosEmpresa).subscribe({
      next: () => {
        this.loginCarregando.set(false);
        this.loginSucesso.set('Cadastro realizado com sucesso! Faça login para continuar.');
        this.formularioEmpresa.reset();
        this.formularioEnviado.set(false);
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

  protected deveMostrarErro(controle: FormControl<string>): boolean {
    return controle.invalid && (controle.touched || this.formularioEnviado());
  }

  protected deveMostrarErroSenhasIguais(formulario: FormGroup): boolean {
    if (formulario.errors?.['senhasDiferentes'] !== true) {
      return false;
    }
    const confirmar = formulario.get('confirmarSenha');
    return Boolean(confirmar?.touched) || this.formularioEnviado();
  }
}

function senhasIguais(grupo: AbstractControl): ValidationErrors | null {
  const senha = grupo.get('senha')?.value;
  const confirmar = grupo.get('confirmarSenha')?.value;

  if (!senha || !confirmar) {
    return null;
  }

  return senha === confirmar ? null : { senhasDiferentes: true };
}
