import { Routes } from '@angular/router';

import { AutenticadoGuard } from './guards/autenticado.guard';
import { EmpresaGuard } from './guards/empresa.guard';
import { ExtratoGuard } from './guards/extrato.guard';

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
    path: 'home',
    title: 'Home | Moeda Estudantil',
    canActivate: [AutenticadoGuard],
    loadComponent: () =>
      import('./pages/home/home-page').then((component) => component.HomePage),
  },
  {
    path: 'extrato',
    title: 'Extrato | Moeda Estudantil',
    canActivate: [ExtratoGuard],
    loadComponent: () =>
      import('./pages/extrato/extrato-page').then((component) => component.ExtratoPage),
  },
  {
    path: 'beneficios',
    title: 'Gerenciar Benefícios | Moeda Estudantil',
    canMatch: [EmpresaGuard],
    loadComponent: () =>
      import('./pages/empresas/beneficios/empresa-beneficios-page').then(
        (component) => component.EmpresaBeneficiosPage,
      ),
  },
  {
    path: '**',
    redirectTo: '',
  },
];
