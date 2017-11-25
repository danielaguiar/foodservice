package com.gestaosimples.servico.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author daniel.aguiar
 *
 */
@Entity
@Table(name = "t_telefone")
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1847904330633748314L;

    @Id
    @Column(name = "id_telefone")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NR_FONE_RESIDENCIAL")
    private String nrFoneResidencial;

    @Column(name = "NR_FONE_CELULAR")
    private String nrFoneCelular;

    @Column(name = "NR_FONE_WHATSAPP")
    private String nrFoneWhatsapp;

    public Telefone() {
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

}
