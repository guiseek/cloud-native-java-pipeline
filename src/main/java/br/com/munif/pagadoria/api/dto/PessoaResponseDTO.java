package br.com.munif.pagadoria.api.dto;

import java.util.UUID;

public record PessoaResponseDTO(
        UUID id,
        String nome,
        String cpfCnpj,
        String telefonePrincipal,
        String telefoneSecundario,
        String email,
        String cep,
        String endereco,
        String complemento,
        String bairro,
        String cidade,
        String uf
) {}