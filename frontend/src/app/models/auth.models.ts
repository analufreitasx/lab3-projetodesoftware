export type ModoAutenticacao = 'login' | 'cadastro-aluno' | 'cadastro-empresa';

export interface DadosLogin {
  email: string;
  senha: string;
}

export interface DadosCadastroAluno {
  nome: string;
  cpf: string;
  rg: string;
  endereco: string;
  instituicao: string;
  curso: string;
  email: string;
  senha: string;
}

export interface DadosCadastroEmpresa {
  nome: string;
  email: string;
  senha: string;
  cnpj: string;
}

export interface LoginResponse {
  tokenType: string;
  accessToken: string;
  expiresIn: number;
  perfil: string;
}
