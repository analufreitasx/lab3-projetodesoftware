import { ChangeDetectionStrategy, Component, inject } from '@angular/core';

import { DadosMockService } from '../../../services/dados-mock.service';

@Component({
  selector: 'app-empresa-home-page',
  templateUrl: './empresa-home-page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmpresaHomePage {
  private readonly dadosMockService = inject(DadosMockService);

  protected readonly beneficiosMaisVendidos = this.dadosMockService.beneficiosMaisVendidos;
}
