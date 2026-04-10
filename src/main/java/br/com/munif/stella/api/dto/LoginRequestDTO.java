package br.com.munif.stella.api.dto;

public record LoginRequestDTO(
        String username,
        String password
) {
}