package com.gestaosimples.corp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_email")
public class Email implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    @Id
    private Long id;

    @Column(name = "ed_email", length = 120)
    private String edEmail;

    @MapsId
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private Pessoa pessoa;

    public Email() {
    }

    public Email(Long id, String edEmail, Pessoa pessoa) {
        super();
        this.id = id;
        this.edEmail = edEmail;
        this.pessoa = pessoa;
    }

    public Email(String edEmail) {
        super();
        this.edEmail = edEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getEdEmail() {
        return edEmail;
    }

    public void setEdEmail(String edEmail) {
        this.edEmail = edEmail;
    }

}
