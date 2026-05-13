package br.pucminas.karv_coins.dto.response;

import br.pucminas.karv_coins.model.Aluno;

public record AlunoResponseDto(
        Long id,
        String nome,
        String email,
        String cpf,
        String rg,
        String curso,
        String instituicao,
        String endereco,
        Double saldo,
        String perfil
) {
    public static AlunoResponseDto from(Aluno aluno) {
        return new AlunoResponseDto(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getRg(),
                aluno.getCurso(),
                aluno.getInstituicao(),
                aluno.getEndereco(),
                aluno.getSaldo(),
                aluno.getPerfil().name()
        );
    }
}
