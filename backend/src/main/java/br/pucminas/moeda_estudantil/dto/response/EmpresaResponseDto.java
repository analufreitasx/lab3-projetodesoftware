package br.pucminas.moeda_estudantil.dto.response;

import br.pucminas.moeda_estudantil.model.Empresa;

public record EmpresaResponseDto(
        Long id,
        String nome,
        String email,
        String cnpj,
        String perfil
) {
    public static EmpresaResponseDto from(Empresa empresa) {
        return new EmpresaResponseDto(
                empresa.getId(),
                empresa.getNome(),
                empresa.getEmail(),
                empresa.getCnpj(),
                empresa.getPerfil().name()
        );
    }
}
