import { ChangeDetectionStrategy, Component, input, output } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

export type HeaderActionVariant = 'solid' | 'outline';

export interface HeaderNavItem {
  label: string;
  rota: string;
}

@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './header.html',
  styleUrl: './header.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
  host: {
    class: 'block sticky top-0 z-50',
  },
})
export class Header {
  readonly actionLabel = input.required<string>();
  readonly actionVariant = input<HeaderActionVariant>('solid');
  readonly saldoLabel = input<string | null>(null);
  readonly itensNavegacao = input<HeaderNavItem[]>([]);
  readonly actionClick = output<void>();

  protected onActionClick(): void {
    this.actionClick.emit();
  }
}
