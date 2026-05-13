import { ChangeDetectionStrategy, Component } from '@angular/core';

interface LancamentoProfessor {
  id: number;
  aluno: string;
  motivo: string;
  quantidadeMoedas: number;
  data: string;
}

@Component({
  selector: 'app-professor-extrato-page',
  templateUrl: './professor-extrato-page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProfessorExtratoPage {
  protected readonly lancamentosProfessor: LancamentoProfessor[] = [
    { id: 1, aluno: 'Mariana Souza', motivo: 'Seminário destaque', quantidadeMoedas: -50, data: '12/05/2026' },
    { id: 2, aluno: 'Pedro Almeida', motivo: 'Lista extra resolvida', quantidadeMoedas: -25, data: '10/05/2026' },
    { id: 3, aluno: 'Lívia Fernandes', motivo: 'Apoio a colegas em laboratório', quantidadeMoedas: -50, data: '07/05/2026' },
  ];
}
