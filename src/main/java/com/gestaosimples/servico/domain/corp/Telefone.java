package com.gestaosimples.servico.domain.corp;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.gestaosimples.servico.domain.dto.TelefoneDTO;

/**
 * @author daniel.aguiar
 *
 */
@Entity
@Table(name = "t_telefone")
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1847904330633748314L;

    @Id
    private Long id;

    @Column(name = "NR_FONE_RESIDENCIAL")
    private String nrFoneResidencial;

    @Column(name = "NR_FONE_CELULAR")
    private String nrFoneCelular;

    @Column(name = "NR_FONE_WHATSAPP")
    private String nrFoneWhatsapp;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private Pessoa pessoa;

    public Telefone() {
    }

    public Telefone(TelefoneDTO telefone) {
        this.id = telefone.getId();
        this.nrFoneResidencial = telefone.getNrFoneResidencial();
        this.nrFoneCelular = telefone.getNrFoneCelular();
        this.nrFoneWhatsapp = telefone.getNrFoneWhatsapp();
    }

    public Telefone(Long id, String nrFoneResidencial, String nrFoneCelular, String nrFoneWhatsapp) {
        super();
        this.id = id;
        this.nrFoneResidencial = nrFoneResidencial;
        this.nrFoneCelular = nrFoneCelular;
        this.nrFoneWhatsapp = nrFoneWhatsapp;
    }

    public Telefone(String nrFoneResidencial, String nrFoneCelular, String nrFoneWhatsapp) {
        super();
        this.nrFoneResidencial = nrFoneResidencial;
        this.nrFoneCelular = nrFoneCelular;
        this.nrFoneWhatsapp = nrFoneWhatsapp;
    }

    public Telefone(TelefoneDTO telefone, Pessoa pessoa) {
        this(telefone);
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNrFoneResidencial() {
        return nrFoneResidencial;
    }

    public void setNrFoneResidencial(String nrFoneResidencial) {
        this.nrFoneResidencial = nrFoneResidencial;
    }

    public String getNrFoneCelular() {
        return nrFoneCelular;
    }

    public void setNrFoneCelular(String nrFoneCelular) {
        this.nrFoneCelular = nrFoneCelular;
    }

    public String getNrFoneWhatsapp() {
        return nrFoneWhatsapp;
    }

    public void setNrFoneWhatsapp(String nrFoneWhatsapp) {
        this.nrFoneWhatsapp = nrFoneWhatsapp;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Telefone other = (Telefone) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
