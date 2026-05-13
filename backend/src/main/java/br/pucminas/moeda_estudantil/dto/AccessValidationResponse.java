package br.pucminas.moeda_estudantil.dto;

public record AccessValidationResponse(
        boolean valid,
        String email,
        String perfil
) {
}
