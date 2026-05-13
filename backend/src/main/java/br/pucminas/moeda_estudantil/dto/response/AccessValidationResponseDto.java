package br.pucminas.moeda_estudantil.dto.response;

public record AccessValidationResponseDto(
        boolean valid,
        String email,
        String perfil
) {
}
