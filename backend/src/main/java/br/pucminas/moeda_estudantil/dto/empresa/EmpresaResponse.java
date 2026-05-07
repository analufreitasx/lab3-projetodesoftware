package br.pucminas.moeda_estudantil.dto.empresa;

import br.pucminas.moeda_estudantil.model.Empresa;

public record EmpresaResponse(
        Long id,
        String nome,
        String login,
        String email,
        String cnpj,
        String perfil
) {
    public static EmpresaResponse from(Empresa empresa) {
        return new EmpresaResponse(
                empresa.getId(),
                empresa.getNome(),
                empresa.getLogin(),
                empresa.getEmail(),
                empresa.getCnpj(),
                empresa.getPerfil().name()
        );
    }
}
