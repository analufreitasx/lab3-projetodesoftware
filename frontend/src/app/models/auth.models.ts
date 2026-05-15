export type ModoAutenticacao = 'login' | 'cadastro-aluno' | 'cadastro-empresa';
export type PerfilUsuario = 'ALUNO' | 'PROFESSOR' | 'EMPRESA';

export interface DadosLogin {
  email: string;
  senha: string;
}

export interface DadosEndereco {
  cep: string;
  rua: string;
  numero: string;
  complemento: string;
  bairro: string;
  cidade: string;
  uf: string;
}

export interface DadosCadastroAluno {
  nome: string;
  cpf: string;
  rg: string;
  endereco: DadosEndereco;
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
  perfil: PerfilUsuario;
}
