package com.gestaosimples.corp.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.gestaosimples.servico.domain.Empresa;

@Entity
@Table(name = "t_unidade_medida")
public class UnidadeMedida implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 9, name = "id_unidade_medida")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @Column(name = "nm_unidade_medida", length = 60)
    private String nome;

    @Column(name = "sg_unidade_medida", length = 5)
    private String sigla;

    public UnidadeMedida() {
    }

    public UnidadeMedida(Long id, Empresa empresa, String nome, String sigla) {
        super();
        this.id = id;
        this.empresa = empresa;
        this.nome = nome;
        this.sigla = sigla;
    }

    public UnidadeMedida(Empresa empresa, String nome, String sigla) {
        this(null, empresa, nome, sigla);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
