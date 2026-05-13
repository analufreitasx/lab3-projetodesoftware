import { ChangeDetectionStrategy, Component, inject } from '@angular/core';

import { DadosMockService } from '../../../services/dados-mock.service';

@Component({
  selector: 'app-aluno-home-page',
  templateUrl: './aluno-home-page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AlunoHomePage {
  private readonly dadosMockService = inject(DadosMockService);

  protected readonly saldoAluno = this.dadosMockService.saldoAluno;
  protected readonly beneficiosDisponiveis = this.dadosMockService.beneficiosDisponiveis;
}
