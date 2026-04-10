package br.com.munif.stella.api.dto;

import java.util.UUID;

public record PessoaResumoDTO(
        UUID id,
        String nome
) {
}
