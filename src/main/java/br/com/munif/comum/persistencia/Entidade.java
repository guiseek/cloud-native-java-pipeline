package br.com.munif.comum.persistencia;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class Entidade implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm;

    @Column(name = "alterado_em", nullable = false)
    private Instant alteradoEm;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "extra", length = 200)
    private String extra;

    @Column(name = "oi", length = 100)
    private String oi;

    @PrePersist
    public void prePersist() {
        Instant agora = Instant.now();
        this.criadoEm = agora;
        this.alteradoEm = agora;

        if (!this.ativo) {
            this.ativo = true;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.alteradoEm = Instant.now();
    }

    public boolean isNova() {
        return this.id == null;
    }

    public void excluirLogicamente() {
        this.ativo = false;
    }

    public void reativar() {
        this.ativo = true;
    }

}