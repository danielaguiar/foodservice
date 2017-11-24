package com.gestaosimples.servico.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import com.gestaosimples.servico.domain.corp.Pessoa;

/**
 * DOCUMENT ME!
 *
 */
@Entity(name = "t_cliente")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 6356730187701538408L;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Empresa() {

    }

    public Empresa(Pessoa pessoa) {
        super();
        this.pessoa = pessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
