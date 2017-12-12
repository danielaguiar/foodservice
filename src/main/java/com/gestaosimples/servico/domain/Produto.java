package com.gestaosimples.servico.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import com.gestaosimples.servico.domain.enuns.SimNao;
import com.gestaosimples.servico.domain.enuns.Status;

@Entity(name = "t_produto")
public class Produto implements Serializable {

    /**  */
    private static final long serialVersionUID = 7580079190477567443L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 9, name = "id_produto")
    private Long id;

    @Column(name = "nm_produto", length = 100)
    private String nmProduto;

    @Column(name = "vl_preco", length = 10)
    private Double preço;

    @Enumerated(EnumType.STRING)
    @Column(name = "st_possuiItens", length = 1)
    private SimNao possuiItens;

    @Enumerated(EnumType.STRING)
    @Column(name = "st_produto", length = 1)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @ManyToMany
    @JoinTable(name = "t_produto_categoria", joinColumns = @JoinColumn(name = "id_produto"), inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categorias = new ArrayList<Categoria>();

    public Produto() {
    }

    public Produto(Long id) {
        super();
        this.id = id;
    }

    public Produto(Long id, String nmProduto, Double preço) {
        super();
        this.id = id;
        this.nmProduto = nmProduto;
        this.preço = preço;
    }

    public Produto(String nmProduto, Double preço) {
        super();
        this.nmProduto = nmProduto;
        this.preço = preço;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmProduto() {
        return nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public Double getPreço() {
        return preço;
    }

    public void setPreço(Double preço) {
        this.preço = preço;
    }

    public SimNao getPossuiItens() {
        return possuiItens;
    }

    public void setPossuiItens(SimNao possuiItens) {
        this.possuiItens = possuiItens;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}