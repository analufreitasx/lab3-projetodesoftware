package br.pucminas.moeda_estudantil.dto.response;

public record LoginResponseDto(
        String tokenType,
        String accessToken,
        Long expiresIn,
        String perfil
) {
}
