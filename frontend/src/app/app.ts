import { ChangeDetectionStrategy, Component, computed, inject, signal } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { filter } from 'rxjs';

import { Header, HeaderActionVariant, HeaderNavItem } from './components/header/header';
import { AuthService } from './services/auth.service';
import { DadosMockService } from './services/dados-mock.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Header],
  templateUrl: './app.html',
  styleUrl: './app.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class App {
  private readonly router = inject(Router);
  private readonly authService = inject(AuthService);
  private readonly dadosMockService = inject(DadosMockService);

  protected readonly currentUrl = signal(this.router.url);
  protected readonly isAuthPage = computed(() => this.currentUrl().startsWith('/auth'));
  protected readonly estaEmAreaAutenticada = computed(() => this.authService.estaAutenticado());
  protected readonly headerActionLabel = computed(() => {
    if (this.estaEmAreaAutenticada() && !this.isAuthPage()) {
      return 'Sair';
    }

    return this.isAuthPage() ? 'Voltar' : 'Faça Login';
  });
  protected readonly headerActionVariant = computed<HeaderActionVariant>(() =>
    this.isAuthPage() || this.estaEmAreaAutenticada() ? 'outline' : 'solid',
  );
  protected readonly saldoHeader = computed(() => {
    switch (this.authService.perfilAtual()) {
      case 'ALUNO':
        return `Saldo: ${this.dadosMockService.saldoAluno()} moedas`;
      case 'PROFESSOR':
        return `Saldo: ${this.dadosMockService.saldoProfessor()} moedas`;
      default:
        return null;
    }
  });
  protected readonly itensNavegacaoHeader = computed<HeaderNavItem[]>(() => {
    switch (this.authService.perfilAtual()) {
      case 'ALUNO':
        return [
          { label: 'Home', rota: '/home' },
          { label: 'Extrato', rota: '/extrato' },
        ];
      case 'PROFESSOR':
        return [
          { label: 'Home', rota: '/home' },
          { label: 'Extrato', rota: '/extrato' },
        ];
      case 'EMPRESA':
        return [
          { label: 'Home', rota: '/home' },
          { label: 'Gerenciar Benefícios', rota: '/beneficios' },
        ];
      default:
        return [];
    }
  });

  constructor() {
    this.router.events.pipe(filter((event) => event instanceof NavigationEnd)).subscribe((event) => {
      this.currentUrl.set(event.urlAfterRedirects);
    });
  }

  protected onHeaderAction(): void {
    if (this.estaEmAreaAutenticada() && !this.isAuthPage()) {
      this.authService.sair();
      void this.router.navigateByUrl('/auth');
      return;
    }

    void this.router.navigateByUrl(this.isAuthPage() ? '/' : '/auth');
  }
}
