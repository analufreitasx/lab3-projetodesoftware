import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    title: 'Moeda Estudantil',
    loadComponent: () =>
      import('./pages/landing-page/landing-page').then((component) => component.LandingPage),
  },
  {
    path: 'auth',
    title: 'Entrar | Moeda Estudantil',
    loadComponent: () => import('./pages/auth/auth').then((component) => component.AuthPage),
  },
  {
    path: '**',
    redirectTo: '',
  },
];
