import { ChangeDetectionStrategy, Component } from '@angular/core';
import { NgOptimizedImage } from '@angular/common';

interface FeatureCard {
  title: string;
  description: string;
}

interface StepCard {
  step: string;
  title: string;
  description: string;
}

interface TeamMember {
  name: string;
  role: string;
}

@Component({
  selector: 'app-landing-page',
  imports: [NgOptimizedImage],
  templateUrl: './landing-page.html',
  styleUrl: './landing-page.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LandingPage {
  protected readonly roles: FeatureCard[] = [
    {
      title: 'Alunos',
      description: 'Acompanham saldo, extrato e trocam moedas por benefícios oferecidos por empresas parceiras.',
    },
    {
      title: 'Professores',
      description: 'Reconhecem desempenho, participação e boas atitudes enviando moedas com uma justificativa.',
    },
    {
      title: 'Empresas parceiras',
      description: 'Disponibilizam vantagens reais e validam cupons gerados nos resgates dos estudantes.',
    },
  ];

  protected readonly steps: StepCard[] = [
    {
      step: '01',
      title: 'Reconheça o mérito',
      description: 'Professores distribuem moedas para alunos por participação, evolução e desempenho acadêmico.',
    },
    {
      step: '02',
      title: 'Acompanhe a jornada',
      description: 'Alunos visualizam saldo e histórico completo das transações dentro da plataforma.',
    },
    {
      step: '03',
      title: 'Resgate vantagens',
      description: 'Moedas acumuladas viram cupons para descontos, materiais e outros benefícios parceiros.',
    },
  ];

  protected readonly team: TeamMember[] = [
    { name: 'Ana Luiza', role: 'Desenvolvimento' },
    { name: 'Kayke Emanoel', role: 'Desenvolvimento' },
    { name: 'Renato Douglas', role: 'Desenvolvimento' },
    { name: 'Vicenzo Fonseca', role: 'Desenvolvimento' },
  ];
}
