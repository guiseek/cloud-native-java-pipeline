package br.com.munif.pagadoria.api.dto;

public record LoginRequestDTO(
        String username,
        String password
) {
}