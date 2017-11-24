package com.gestaosimples.servico.domain.corp;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "CD_TIPO_PESSOA", length = 1, discriminatorType = DiscriminatorType.STRING)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = -5148742405028232963L;

    @Id
    @Column(name = "ID_PESSOA", nullable = false)
    @SequenceGenerator(name = "SQ_PESSOA", sequenceName = "CORPORATIVO_MAPA.SQ_PESSOA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESSOA")
    protected Long idPessoa;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private Set<Endereco> enderecos;

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

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
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