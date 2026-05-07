# Modelo ER - Notação de Peter Chen

A **Notação de Chen** é uma forma clássica e muito expressiva de modelar dados conceitualmente. Ao contrário da notação Pé-de-Galinha (Crow's Foot) que foca em tabelas, a notação de Chen foca na semântica real:
- **Entidades** são representadas por retângulos.
- **Relacionamentos** são representados por losangos.
- **Atributos** são representados por elipses (com os atributos identificadores/chaves primárias sublinhados).

Uma das belezas da Notação de Chen é permitir que **Relacionamentos tenham atributos**, o que se aplica perfeitamente às `TRANSAÇÕES` (envio de moedas) e aos `RESGATES` no nosso sistema.

Abaixo, utilizei a ferramenta Flowchart do Mermaid para simular as formas geométricas exatas da notação de Chen:

```mermaid
flowchart TD
    %% Definições de estilo simulando a Notação de Chen
    classDef entity fill:#f4f4f5,stroke:#3f3f46,stroke-width:2px,color:#18181b,font-weight:bold;
    classDef relationship fill:#e0f2fe,stroke:#0284c7,stroke-width:2px,color:#0c4a6e;
    classDef attribute fill:#ffffff,stroke:#a1a1aa,stroke-width:1px,color:#3f3f46;

    %% 1. Entidades (Retângulos)
    E_INST[INSTITUIÇÃO]:::entity
    E_ALUNO[ALUNO]:::entity
    E_PROF[PROFESSOR]:::entity
    E_CURSO[CURSO]:::entity
    E_EMP[EMPRESA PARCEIRA]:::entity
    E_VANT[VANTAGEM]:::entity
    E_COTA[COTA SEMESTRAL]:::entity

    %% 2. Relacionamentos (Losangos)
    R_MATRICULA{matricula}:::relationship
    R_EMPREGA{emprega}:::relationship
    R_REGISTRA{registra}:::relationship
    R_AGRUPA{agrupa}:::relationship
    R_OFERECE{oferece}:::relationship
    R_ENVIA{envia moedas}:::relationship
    R_RESGATA{resgata}:::relationship
    R_POSSUI_COTA{recebe}:::relationship

    %% 3. Conexões e Cardinalidades
    E_INST ---|1| R_MATRICULA ---|N| E_ALUNO
    E_INST ---|1| R_EMPREGA ---|N| E_PROF
    E_INST ---|1| R_REGISTRA ---|N| E_CURSO
    E_CURSO ---|1| R_AGRUPA ---|N| E_ALUNO
    E_EMP ---|1| R_OFERECE ---|N| E_VANT
    E_PROF ---|1| R_ENVIA ---|N| E_ALUNO
    E_ALUNO ---|1| R_RESGATA ---|N| E_VANT
    E_PROF ---|1| R_POSSUI_COTA ---|N| E_COTA

    %% 4. Atributos (Elipses/Stadiums)
    
    %% Instituição
    A_I1([<u>id</u>]):::attribute --- E_INST
    A_I2([nome]):::attribute --- E_INST
    A_I3([endereco]):::attribute --- E_INST
    A_I4([email]):::attribute --- E_INST
    A_I5([telefone]):::attribute --- E_INST

    %% Aluno
    A_A1([<u>id</u>]):::attribute --- E_ALUNO
    A_A2([nome]):::attribute --- E_ALUNO
    A_A3([email]):::attribute --- E_ALUNO
    A_A4([cpf]):::attribute --- E_ALUNO
    A_A5([rg]):::attribute --- E_ALUNO
    A_A6([endereco]):::attribute --- E_ALUNO
    A_A7([senha]):::attribute --- E_ALUNO
    A_A8([saldo]):::attribute --- E_ALUNO

    %% Professor
    A_P1([<u>id</u>]):::attribute --- E_PROF
    A_P2([nome]):::attribute --- E_PROF
    A_P3([cpf]):::attribute --- E_PROF
    A_P4([departamento]):::attribute --- E_PROF
    A_P5([email]):::attribute --- E_PROF
    A_P6([senha]):::attribute --- E_PROF
    A_P7([saldo]):::attribute --- E_PROF

    %% Curso
    A_C1([<u>id</u>]):::attribute --- E_CURSO
    A_C2([nome]):::attribute --- E_CURSO

    %% Empresa Parceira
    A_EP1([<u>id</u>]):::attribute --- E_EMP
    A_EP2([nome]):::attribute --- E_EMP
    A_EP3([email]):::attribute --- E_EMP
    A_EP4([senha]):::attribute --- E_EMP

    %% Vantagem
    A_V1([<u>id</u>]):::attribute --- E_VANT
    A_V2([descricao]):::attribute --- E_VANT
    A_V3([foto_url]):::attribute --- E_VANT
    A_V4([custo_moedas]):::attribute --- E_VANT

    %% Cota Semestral
    A_CS1([<u>id</u>]):::attribute --- E_COTA
    A_CS2([data]):::attribute --- E_COTA
    A_CS3([valor]):::attribute --- E_COTA

    %% Atributos de Relacionamentos (Diferencial de Chen)
    %% Transações
    A_T1([valor]):::attribute --- R_ENVIA
    A_T2([motivo]):::attribute --- R_ENVIA
    A_T3([data]):::attribute --- R_ENVIA

    %% Resgates
    A_R1([valor_moedas]):::attribute --- R_RESGATA
    A_R2([codigo_cupom]):::attribute --- R_RESGATA
    A_R3([data]):::attribute --- R_RESGATA
```

### Pontos de Destaque da Abordagem de Chen:
1. **Transações e Resgates não são Entidades aqui**: Em bancos relacionais, eles viram tabelas. Na modelagem conceitual de Chen, eles são a própria "ação" (o relacionamento *envia moedas* e *resgata*), que contém atributos de data, valor, etc.
2. **Cardinalidade (`1:N`) explícita**: Fica claro que uma *Instituição* possui múltiplos *Alunos*, mas um *Aluno* pertence a apenas uma *Instituição*.
3. **Identificadores**: Os campos que formam a Chave Primária (PK) estão sublinhados (`id`), como manda a notação oficial.
