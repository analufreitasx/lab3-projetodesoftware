import { ChangeDetectionStrategy, Component, computed, inject, signal } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { filter } from 'rxjs';

import { Header, HeaderActionVariant } from './components/header/header';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Header],
  templateUrl: './app.html',
  styleUrl: './app.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class App {
  private readonly router = inject(Router);

  protected readonly currentUrl = signal(this.router.url);
  protected readonly isAuthPage = computed(() => this.currentUrl().startsWith('/auth'));
  protected readonly headerActionLabel = computed(() => (this.isAuthPage() ? 'Voltar' : 'Faça Login'));
  protected readonly headerActionVariant = computed<HeaderActionVariant>(() =>
    this.isAuthPage() ? 'outline' : 'solid',
  );

  constructor() {
    this.router.events.pipe(filter((event) => event instanceof NavigationEnd)).subscribe((event) => {
      this.currentUrl.set(event.urlAfterRedirects);
    });
  }

  protected onHeaderAction(): void {
    void this.router.navigateByUrl(this.isAuthPage() ? '/' : '/auth');
  }
}
