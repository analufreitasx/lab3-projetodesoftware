import { inject, Injectable } from '@angular/core';
import { CanMatch, Router, UrlTree } from '@angular/router';

import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class AlunoGuard implements CanMatch {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  canMatch(): boolean | UrlTree {
    if (!this.authService.estaAutenticado()) {
      return this.router.parseUrl('/auth');
    }

    return this.authService.usuarioTemPerfil('ALUNO');
  }
}
