import { ChangeDetectionStrategy, Component, inject } from '@angular/core';

import { AlunoHomePage } from '../alunos/home/aluno-home-page';
import { EmpresaHomePage } from '../empresas/home/empresa-home-page';
import { ProfessorHomePage } from '../professores/home/professor-home-page';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home-page',
  imports: [AlunoHomePage, EmpresaHomePage, ProfessorHomePage],
  templateUrl: './home-page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HomePage {
  private readonly authService = inject(AuthService);

  protected readonly perfilAtual = this.authService.perfilAtual;
}
