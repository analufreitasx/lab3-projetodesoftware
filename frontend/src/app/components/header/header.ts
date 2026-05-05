import { ChangeDetectionStrategy, Component, input, output } from '@angular/core';

export type HeaderActionVariant = 'solid' | 'outline';

@Component({
  selector: 'app-header',
  imports: [],
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
  readonly actionClick = output<void>();

  protected onActionClick(): void {
    this.actionClick.emit();
  }
}
