package com.gestaosimples.corp.dto;

import java.io.Serializable;
import com.gestaosimples.corp.domain.Telefone;

public class TelefoneDTO implements Serializable {

    private static final long serialVersionUID = 1847904330633748314L;

    private Long id;

    private String nrFoneResidencial;

    private String nrFoneCelular;

    private String nrFoneWhatsapp;

    public TelefoneDTO() {
    }

    public TelefoneDTO(Long id, String nrFoneResidencial, String nrFoneCelular, String nrFoneWhatsapp) {
        super();
        this.id = id;
        this.nrFoneResidencial = nrFoneResidencial;
        this.nrFoneCelular = nrFoneCelular;
        this.nrFoneWhatsapp = nrFoneWhatsapp;
    }

    public TelefoneDTO(Telefone telefone) {
        this.id = telefone.getId();
        this.nrFoneResidencial = telefone.getNrFoneResidencial();
        this.nrFoneCelular = telefone.getNrFoneCelular();
        this.nrFoneWhatsapp = telefone.getNrFoneWhatsapp();
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
