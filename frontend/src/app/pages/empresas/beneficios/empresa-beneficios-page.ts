import { ChangeDetectionStrategy, Component, inject } from '@angular/core';

import { DadosMockService } from '../../../services/dados-mock.service';

@Component({
  selector: 'app-empresa-beneficios-page',
  templateUrl: './empresa-beneficios-page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmpresaBeneficiosPage {
  private readonly dadosMockService = inject(DadosMockService);

  protected readonly beneficiosGerenciados = this.dadosMockService.beneficiosMaisVendidos;
}
