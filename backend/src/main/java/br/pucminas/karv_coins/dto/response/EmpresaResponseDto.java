package br.pucminas.karv_coins.dto.response;

import br.pucminas.karv_coins.model.Empresa;

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
