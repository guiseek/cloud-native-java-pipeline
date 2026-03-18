package br.com.munif.pagadoria.api.dto;

public record PessoaCreateDTO(
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

