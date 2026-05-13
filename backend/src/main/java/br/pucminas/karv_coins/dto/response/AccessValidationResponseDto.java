package br.pucminas.karv_coins.dto.response;

public record AccessValidationResponseDto(
        boolean valid,
        String email,
        String perfil
) {
}
