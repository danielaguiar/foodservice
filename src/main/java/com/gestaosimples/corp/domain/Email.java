package com.gestaosimples.corp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_email")
public class Email implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 9, name = "id_email")
    private Long id;

    @Column(name = "ed_email", length = 120)
    private String edEmail;

    public Email() {
    }

    public Email(Long id, String edEmail) {
        super();
        this.id = id;
        this.edEmail = edEmail;
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

    public String getEdEmail() {
        return edEmail;
    }

    public void setEdEmail(String edEmail) {
        this.edEmail = edEmail;
    }

}
