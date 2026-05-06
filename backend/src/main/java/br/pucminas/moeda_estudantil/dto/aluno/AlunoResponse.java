package br.pucminas.moeda_estudantil.dto.aluno;

import br.pucminas.moeda_estudantil.model.Aluno;

public record AlunoResponse(
        Long id,
        String nome,
        String login,
        String email,
        String cpf,
        String rg,
        String curso,
        String instituicao,
        String perfil
) {
    public static AlunoResponse from(Aluno aluno) {
        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getLogin(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getRg(),
                aluno.getCurso(),
                aluno.getInstituicao(),
                aluno.getPerfil().name()
        );
    }
}
