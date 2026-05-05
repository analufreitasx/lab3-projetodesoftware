package br.pucminas.moeda_estudantil.dto;

public record LoginResponse(
        String tokenType,
        String accessToken,
        Long expiresIn,
        String perfil
) {
}
