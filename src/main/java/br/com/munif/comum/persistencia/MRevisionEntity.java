package br.com.munif.comum.persistencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "versao")
@RevisionEntity(MRevisionEntityListener.class)
public class MRevisionEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @RevisionTimestamp
    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "ip", length = 45)
    private String ip;

    @Column(name = "usuario", length = 100)
    private String usuario;

    @Column(name = "oi", length = 100)
    private String oi;

    public Long getId() {
        return id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getIp() {
        return ip;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getOi() {
        return oi;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setOi(String oi) {
        this.oi = oi;
    }
}