package br.com.munif.comum.persistencia;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class Entidade implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Instant getAlteradoEm() {
        return alteradoEm;
    }

    public void setAlteradoEm(Instant alteradoEm) {
        this.alteradoEm = alteradoEm;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getOi() {
        return oi;
    }

    public void setOi(String oi) {
        this.oi = oi;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entidade entidade = (Entidade) o;
        return id != null && Objects.equals(id, entidade.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}