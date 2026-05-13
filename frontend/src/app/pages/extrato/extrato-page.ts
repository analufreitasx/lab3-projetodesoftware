import { ChangeDetectionStrategy, Component, inject } from '@angular/core';

import { AlunoExtratoPage } from '../alunos/extrato/aluno-extrato-page';
import { ProfessorExtratoPage } from '../professores/extrato/professor-extrato-page';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-extrato-page',
  imports: [AlunoExtratoPage, ProfessorExtratoPage],
  templateUrl: './extrato-page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ExtratoPage {
  private readonly authService = inject(AuthService);

  protected readonly perfilAtual = this.authService.perfilAtual;
}
