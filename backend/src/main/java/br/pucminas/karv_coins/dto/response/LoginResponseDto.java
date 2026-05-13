package br.pucminas.karv_coins.dto.response;

public record LoginResponseDto(
        String tokenType,
        String accessToken,
        Long expiresIn,
        String perfil
) {
}
