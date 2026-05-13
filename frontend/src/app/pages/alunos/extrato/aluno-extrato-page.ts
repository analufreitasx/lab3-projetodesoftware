import { ChangeDetectionStrategy, Component } from '@angular/core';

interface LancamentoAluno {
  id: number;
  descricao: string;
  origem: string;
  quantidadeMoedas: number;
  data: string;
}

@Component({
  selector: 'app-aluno-extrato-page',
  templateUrl: './aluno-extrato-page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AlunoExtratoPage {
  protected readonly lancamentosAluno: LancamentoAluno[] = [
    {
      id: 1,
      descricao: 'Participação em seminário',
      origem: 'Prof. Carlos Mendes',
      quantidadeMoedas: 80,
      data: '12/05/2026',
    },
    {
      id: 2,
      descricao: 'Entrega antecipada de projeto',
      origem: 'Prof. Helena Duarte',
      quantidadeMoedas: 120,
      data: '08/05/2026',
    },
    {
      id: 3,
      descricao: 'Resgate de benefício',
      origem: 'Café Campus',
      quantidadeMoedas: -120,
      data: '02/05/2026',
    },
  ];
}
