package br.com.munif.pagadoria.api.dto;

public record PessoaUpdateDTO(
        String nome,
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