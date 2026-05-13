

# 🏷️ Sistema de Moeda Estudantil 👨‍💻

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
- [Documentação da API (Swagger)](#-documentação-da-api-swagger)
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

* **Framework:** Angular
* **Linguagem:** TypeScript
* **Estilização:** Tailwind CSS
* **Build Tool:** Angular CLI

### 🖥️ Back-end

* **Linguagem/Runtime:** Java 21 (JDK)
* **Framework:** Spring Boot 4.x
* **Banco de Dados:** SQL Server (Azure SQL Database)
* **ORM / Query Builder:** Hibernate / JPA
* **Autenticação:** JWT (OAuth2 Resource Server) + Spring Security
* **Documentação da API:** springdoc-openapi (Swagger UI)



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

* **Java JDK:** Versão **21** ou superior (Back-end Spring Boot)
* **Node.js:** Versão LTS (v20.x ou superior) (Front-end Angular)
* **Gerenciador de Pacotes:** npm
* **Acesso ao banco SQL Server** configurado em `backend/src/main/resources/application.yaml`

### Variáveis de ambiente

Antes de subir o back-end, exporte:

```powershell
$env:DB_PASSWORD = "<senha-do-banco>"
$env:JWT_SECRET  = "<segredo-do-jwt>"
```

### Back-end

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

A API ficará disponível em `http://localhost:8080/api/moeda-estudantil`.

### Front-end

```powershell
cd frontend
npm install
npm start
```

A aplicação web ficará disponível em `http://localhost:4200`.

---

## 📖 Documentação da API (Swagger)

A documentação interativa dos endpoints é gerada automaticamente pelo springdoc-openapi e fica disponível com o back-end em execução:

| Recurso | URL |
|---|---|
| Swagger UI | `http://localhost:8080/api/moeda-estudantil/swagger-ui.html` |
| OpenAPI (JSON) | `http://localhost:8080/api/moeda-estudantil/v3/api-docs` |

> ℹ️ A interface do Swagger é disponibilizada **apenas para visualização** dos contratos da API (endpoints, parâmetros, schemas dos DTOs e códigos de resposta). Não é o canal recomendado para testes manuais — utilize o front-end ou uma ferramenta como Postman/Insomnia importando o documento OpenAPI.

---

## 📂 Estrutura de Pastas

```
lab3-projetodesoftware/
├── backend/                          # API Spring Boot (Java 21)
│   ├── src/main/java/br/pucminas/moeda_estudantil/
│   │   ├── config/                   # SecurityConfig, OpenApiConfig, AuthDataInitializer
│   │   ├── controller/               # Endpoints REST
│   │   ├── dto/
│   │   │   ├── request/              # *RequestDto
│   │   │   └── response/             # *ResponseDto
│   │   ├── model/                    # Entidades JPA (Usuario, Aluno, Empresa, Professor)
│   │   ├── repository/               # Spring Data JPA
│   │   ├── security/                 # JwtTokenService
│   │   └── service/                  # Regras de negócio
│   └── src/main/resources/
│       └── application.yaml
├── frontend/                         # Aplicação Angular + Tailwind
│   └── src/app/
│       ├── components/
│       ├── config/                   # API_BASE_URL
│       ├── models/
│       ├── pages/
│       └── services/
└── docs/                             # Especificações e diagramas do projeto
```

---


## 👥 Autores

| 👤 Nome | GitHub | 💼 LinkedIn |
|---------|-----------------|-------------|
| Ana Luiza | <div align="center"><a href="https://github.com/analufreitasx"><img src="https://joaopauloaramuni.github.io/image/github6.png" width="50px" height="50px"></a></div> | <div align="center"><a href="https://www.linkedin.com/in/ana-luizadefreitas/"><img src="https://joaopauloaramuni.github.io/image/linkedin2.png" width="50px" height="50px"></a></div> |
| Kayke Emanoel | <div align="center"><a href="https://github.com/Eman134"><img src="https://joaopauloaramuni.github.io/image/github6.png" width="50px" height="50px"></a></div> | <div align="center"><a href="https://www.linkedin.com/in/kaykeeman/"><img src="https://joaopauloaramuni.github.io/image/linkedin2.png" width="50px" height="50px"></a></div> |
| Renato Douglas | <div align="center"><a href="https://github.com/RenatoDNS"><img src="https://joaopauloaramuni.github.io/image/github6.png" width="50px" height="50px"></a></div> | <div align="center"><a href="https://www.linkedin.com/in/renatodns/"><img src="https://joaopauloaramuni.github.io/image/linkedin2.png" width="50px" height="50px"></a></div> |
| Vicenzo Fonseca | <div align="center"><a href="https://github.com/vicenzofms"><img src="https://joaopauloaramuni.github.io/image/github6.png" width="50px" height="50px"></a></div> | <div align="center"><a href="https://www.linkedin.com/in/vicenzo-fonseca/"><img src="https://joaopauloaramuni.github.io/image/linkedin2.png" width="50px" height="50px"></a></div> |

