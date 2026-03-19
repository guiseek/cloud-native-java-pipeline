package br.com.munif.pagadoria.api.dto;

public record LoginResponseDTO(
        String accessToken,
        String refreshToken,
        String tokenType,
        Long expiresIn
) {
}