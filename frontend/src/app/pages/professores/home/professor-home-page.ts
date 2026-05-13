import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';

import { DadosMockService } from '../../../services/dados-mock.service';

@Component({
  selector: 'app-professor-home-page',
  templateUrl: './professor-home-page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProfessorHomePage {
  private readonly dadosMockService = inject(DadosMockService);

  protected readonly saldoProfessor = this.dadosMockService.saldoProfessor;
  protected readonly alunosRelacionados = this.dadosMockService.alunosRelacionados;
  protected readonly totalAlunosRelacionados = this.dadosMockService.totalAlunosRelacionados;
  protected readonly mensagemDistribuicao = signal<string | null>(null);
  protected readonly moedasPorAluno = signal<Record<number, number | null>>({});

  protected atualizarQuantidadeMoedas(alunoId: number, evento: Event): void {
    const campo = evento.target as HTMLInputElement;
    const quantidade = Number(campo.value);

    this.moedasPorAluno.update((valoresAtuais) => ({
      ...valoresAtuais,
      [alunoId]: campo.value ? quantidade : null,
    }));
  }

  protected obterQuantidadeMoedas(alunoId: number): number | null {
    return this.moedasPorAluno()[alunoId] ?? null;
  }

  protected podeDistribuirMoedas(alunoId: number): boolean {
    const quantidade = this.obterQuantidadeMoedas(alunoId);

    return Boolean(quantidade && quantidade > 0 && quantidade <= this.saldoProfessor());
  }

  protected distribuirMoedasParaAluno(alunoId: number, nomeAluno: string): void {
    const quantidade = this.obterQuantidadeMoedas(alunoId);

    if (!quantidade || quantidade <= 0) {
      this.mensagemDistribuicao.set('Informe uma quantidade válida de moedas.');
      return;
    }

    const distribuicaoRealizada = this.dadosMockService.distribuirMoedas(alunoId, quantidade);

    if (!distribuicaoRealizada) {
      this.mensagemDistribuicao.set('Saldo insuficiente para distribuir essa quantidade de moedas.');
      return;
    }

    this.moedasPorAluno.update((valoresAtuais) => ({ ...valoresAtuais, [alunoId]: null }));
    this.mensagemDistribuicao.set(`${quantidade} moedas distribuídas para ${nomeAluno}.`);
  }
}
