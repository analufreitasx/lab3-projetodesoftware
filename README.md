

# 🏷️ Sistema de Moeda Estudantil 👨‍💻

<table>
  <tr>
    <td width="800px">
      <div align="justify">
        Este <b>README.md</b> apresenta um template organizado, ideal para servir como referência acadêmica e profissional em projetos de desenvolvimento, desde trabalhos da faculdade até projetos desenvolvidos para etapas técnicas de processos seletivos. Ele reúne as <i>seções essenciais</i> recomendadas pelo <a href="https://github.com/joaopauloaramuni">Prof. Dr. João Paulo Aramuni</a>, permitindo <i>organização clara</i>, <i>documentação eficiente</i> e <i>padronização</i> entre diferentes trabalhos. O objetivo deste esqueleto é <b>facilitar a construção de projetos bem documentados</b>, oferecendo um <i>guia completo</i> que inclui <b>boas práticas</b>, instruções de execução, tecnologias utilizadas, arquitetura, estruturas de pastas, testes, links úteis e orientações para colaboração. Esse template ajuda estudantes a desenvolverem <b>documentação de qualidade profissional</b> desde os primeiros períodos, promovendo <i>clareza</i>, <i>reprodutibilidade</i> e <i>padronização</i> nos projetos.
      </div>
    </td>
    <td>
      <div>
        <img src="https://joaopauloaramuni.github.io/image/logo_ES_vertical.png" alt="Logo do Projeto" width="120px"/>
      </div>
    </td>
  </tr> 
</table>

---

## 🚧 Status do Projeto

### Exemplos de badges básicos:

[![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/joaopauloaramuni/joaopauloaramuni/main.yml?branch=main)](https://github.com/joaopauloaramuni/joaopauloaramuni/actions/workflows/main.yml)
[![Test Coverage](https://codecov.io/gh/joaopauloaramuni/laboratorio-de-desenvolvimento-de-software/branch/main/graph/badge.svg)](https://codecov.io/gh/joaopauloaramuni/laboratorio-de-desenvolvimento-de-software)
[![Versão](https://img.shields.io/badge/Versão-v1.0.0-blue)](https://github.com/joaopauloaramuni/laboratorio-de-desenvolvimento-de-software/releases)
[![Licença](https://img.shields.io/github/license/joaopauloaramuni/laboratorio-de-desenvolvimento-de-software)](#licença)


## 📚 Índice
- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades Principais](#-funcionalidades-principais)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura](#-arquitetura)
  - [Exemplos de diagramas](#exemplos-de-diagramas)
- [Instalação e Execução](#-instalação-e-execução)
- [Estrutura de Pastas](#-estrutura-de-pastas)
- [Autores](#-autores)
---

## 🔗 Links Úteis
* 🌐 **Demo Online:** [Acesse a Aplicação Web]
  > 💻 **Descrição:** Link para a aplicação em ambiente de produção (Ex: hospedado na Vercel, Netlify ou AWS S3).

## 📝 Sobre o Projeto
O **Sistema de Mérito Estudantil** é uma plataforma web desenvolvida para estimular o reconhecimento acadêmico por meio de uma moeda virtual. Professores distribuem moedas aos alunos como forma de reconhecimento por bom desempenho, participação e comportamento. Os alunos, por sua vez, podem trocar essas moedas por vantagens oferecidas por empresas parceiras, como descontos em restaurantes, mensalidades ou materiais acadêmicos.

## ✨ Funcionalidades Principais
Liste as funcionalidades de forma clara e objetiva.

- 🔐 Autenticação: Login com email e senha obrigatório para todos os perfis — aluno, professor e empresa parceira.
- 📝 Cadastro de Usuários: Registro de alunos com dados pessoais e vínculo à instituição; cadastro autônomo de empresas parceiras; professores inseridos pela instituição.
- 🪙 Distribuição de Moedas: Professores enviam moedas a alunos com motivo obrigatório, sujeito à validação de saldo.
- 📆 Crédito Semestral Automático: Cada professor recebe 1.000 moedas por semestre de forma acumulativa, sem perda do saldo anterior.
- 📋 Extrato de Conta: Professores e alunos consultam saldo atual e histórico completo de transações realizadas.
- 🛍️ Catálogo de Vantagens: Empresas parceiras cadastram benefícios com descrição, foto e custo em moedas; alunos consultam e selecionam o que desejam resgatar.
- 🎁 Resgate de Vantagens: Alunos trocam moedas por vantagens com débito automático do saldo após confirmação.
- 📨 Notificações por Email: Alunos são notificados ao receber moedas; aluno e empresa recebem cupom com código único a cada resgate realizado.

---

## 🛠 Tecnologias Utilizadas

As seguintes ferramentas, frameworks e bibliotecas foram utilizados na construção deste projeto. Recomenda-se o uso das versões listadas (ou superiores) para garantir a compatibilidade.

### 💻 Front-end

* **Framework/Biblioteca:** Angular 
* **Linguagem/Superset:** TypeScript
* **Estilização:** Tailwind CSS, Material UI
* **Build Tool:** Vite

### 🖥️ Back-end

* **Linguagem/Runtime:** Java 17 (JDK)
* **Framework:** Spring Boot 3.x
* **Banco de Dados:** 
* **ORM / Query Builder:** Hibernate/JPA
* **Autenticação:** JWT, OAuth2, Spring Security



## 🏗 Arquitetura

Descreva aqui a **arquitetura completa do sistema**, explicando como as camadas, módulos e componentes foram organizados. Informe também **por que** essa arquitetura foi escolhida e **quais problemas ela ajuda a resolver**.

Você pode incluir:

- **Visão geral da arquitetura** (ex.: camadas, módulos, microsserviços, monólito modular, hexagonal, MVC etc.)
- **Principais componentes** e o papel de cada um
- **Padrões de design adotados** (ex.: Repository, Service Layer, DTOs, Factory, Observer)
- **Fluxo de dados** entre as partes do sistema
- **Tecnologias utilizadas em cada camada**
- **Decisões arquiteturais importantes**
- **Trade-offs** ou limitações relevantes

### Exemplos de diagramas

Para melhor visualização e entendimento da estrutura do sistema, os diagramas principais estão organizados lado a lado.

---

## 🔧 Instalação e Execução

### Pré-requisitos
Certifique-se de que o usuário tenha o ambiente configurado.

* **Java JDK:** Versão **17** ou superior (Necessário para o **Back-end Spring Boot**)
* **Node.js:** Versão LTS (v18.x ou superior) (Necessário para o **Front-end React**)
* **Gerenciador de Pacotes:** npm ou yarn
* **Docker** (Opcional, mas **altamente recomendado** para rodar o Banco de Dados)



## 📂 Estrutura de Pastas


---


## 👥 Autores
Liste os principais contribuidores. Você pode usar links para seus perfis.

| 👤 Nome | 🖼️ Foto | :octocat: GitHub | 💼 LinkedIn | 📤 Gmail |
|---------|----------|-----------------|-------------|-----------|
| Nome 1  | <div align="center"><img src="https://joaopauloaramuni.github.io/image/aramunilogo.png" width="70px" height="70px"></div> | <div align="center"><a href="https://github.com/user1"><img src="https://joaopauloaramuni.github.io/image/github6.png" width="50px" height="50px"></a></div> | <div align="center"><a href="https://www.linkedin.com/in/user1"><img src="https://joaopauloaramuni.github.io/image/linkedin2.png" width="50px" height="50px"></a></div> | <div align="center"><a href="mailto:user1@gmail.com"><img src="https://joaopauloaramuni.github.io/image/gmail3.png" width="50px" height="50px"></a></div> |
| Nome 2  | <div align="center"><img src="https://joaopauloaramuni.github.io/image/aramunilogo.png" width="70px" height="70px"></div> | <div align="center"><a href="https://github.com/user2"><img src="https://joaopauloaramuni.github.io/image/github6.png" width="50px" height="50px"></a></div> | <div align="center"><a href="https://www.linkedin.com/in/user2"><img src="https://joaopauloaramuni.github.io/image/linkedin2.png" width="50px" height="50px"></a></div> | <div align="center"><a href="mailto:user2@gmail.com"><img src="https://joaopauloaramuni.github.io/image/gmail3.png" width="50px" height="50px"></a></div> |


