package br.com.munif.pagadoria.api.entity;

import br.com.munif.comum.persistencia.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "pessoa")

@Getter
@Setter
@NoArgsConstructor
public class Pessoa extends Entidade {

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "cpf_cnpj", nullable = false, length = 14, unique = true)
    private String cpfCnpj;

    @Column(name = "telefone_principal", length = 20)
    private String telefonePrincipal;

    @Column(name = "telefone_secundario", length = 20)
    private String telefoneSecundario;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "cep", length = 8)
    private String cep;

    @Column(name = "endereco", length = 200)
    private String endereco;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "uf", length = 2)
    private String uf;


}