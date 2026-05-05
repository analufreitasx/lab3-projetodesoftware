package br.pucminas.moeda_estudantil.dto;

public record AccessValidationResponse(
        boolean valid,
        String login,
        String perfil
) {
}
