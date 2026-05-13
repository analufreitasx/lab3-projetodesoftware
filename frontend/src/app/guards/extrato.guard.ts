import { inject, Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';

import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class ExtratoGuard implements CanActivate {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  canActivate(): boolean | UrlTree {
    if (!this.authService.estaAutenticado()) {
      return this.router.parseUrl('/auth');
    }

    return this.authService.perfilAtual() === 'EMPRESA' ? this.router.parseUrl('/home') : true;
  }
}
