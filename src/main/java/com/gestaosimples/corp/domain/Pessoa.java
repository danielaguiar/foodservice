package com.gestaosimples.corp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "T_PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "CD_TIPO_PESSOA", length = 1, discriminatorType = DiscriminatorType.STRING)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = -5148742405028232963L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PESSOA", nullable = false)
    protected Long idPessoa;

    public Pessoa() {
    }

    public Pessoa(Long idPessoa) {
        super();
        this.idPessoa = idPessoa;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPessoa == null) ? 0 : idPessoa.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (idPessoa == null) {
            if (other.idPessoa != null)
                return false;
        } else if (!idPessoa.equals(other.idPessoa))
            return false;
        return true;
    }
}