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
import javax.persistence.OneToMany;
import com.gestaosimples.corp.domain.UnidadeMedida;
import com.gestaosimples.servico.domain.dto.ProdutoDTO;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "st_produto_composto", length = 1)
    private SimNao produtoComposto;

    @Enumerated(EnumType.STRING)
    @Column(name = "st_uso_interno", length = 1)
    private SimNao usoInterno;

    @Enumerated(EnumType.STRING)
    @Column(name = "st_produto", length = 1)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @ManyToMany
    @JoinTable(name = "t_produto_categoria", joinColumns = @JoinColumn(name = "id_produto"), inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categorias = new ArrayList<Categoria>();

    @ManyToMany
    @JoinTable(name = "t_produto_unidade_medida", joinColumns = @JoinColumn(name = "id_produto"), inverseJoinColumns = @JoinColumn(name = "id_unidade_medida", referencedColumnName = "id_unidade_medida"))
    private List<UnidadeMedida> unidades = new ArrayList<UnidadeMedida>();

    @Column(name = "vl_preco", length = 10)
    private Double preco;

    @Column(name = "qtd_estoque", length = 10)
    private Integer qtdMinima;

    @Column(name = "qtd_minima_estoque", length = 10)
    private Integer qtdMinimaEstoque;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoComposicao> composicoes;

    @Column(name = "tx_info_complementar")
    private String infoComplementar;

    @Column(name = "tx_info_nutricional")
    private String infoNutricional;

    @Column(name = "url_blog")
    private String urlBlog;

    public Produto() {
    }

    public Produto(Long id, String nmProduto, SimNao produtoComposto, SimNao usoInterno, Status status, Empresa empresa, List<Categoria> categorias,
        List<UnidadeMedida> unidades, Double preco, Integer qtdMinima, Integer qtdMinimaEstoque, List<ProdutoComposicao> composicoes, String infoComplementar,
        String infoNutricional, String urlBlog) {
        super();
        this.id = id;
        this.nmProduto = nmProduto;
        this.produtoComposto = produtoComposto;
        this.usoInterno = usoInterno;
        this.status = status;
        this.empresa = empresa;
        this.categorias = categorias;
        this.unidades = unidades;
        this.preco = preco;
        this.qtdMinima = qtdMinima;
        this.qtdMinimaEstoque = qtdMinimaEstoque;
        this.composicoes = composicoes;
        this.infoComplementar = infoComplementar;
        this.infoNutricional = infoNutricional;
        this.urlBlog = urlBlog;
    }

    public Produto(ProdutoDTO dto) {
        super();
        this.id = dto.getIdProduto();
        this.empresa = new Empresa(dto.getIdEmpresa());
        this.nmProduto = dto.getNmProduto();
        this.produtoComposto = dto.getProdutoComposto();
        this.usoInterno = dto.getUsoInterno();
        this.status = dto.getStatus();
        this.unidades = dto.getUnidades();
        this.categorias = dto.getCategorias();
        this.preco = dto.getPreco();
        this.qtdMinima = dto.getQtdMinima();
        this.qtdMinimaEstoque = dto.getQtdMinima();
        this.composicoes = dto.getComposicoes();
        this.infoComplementar = dto.getInfoComplementar();
        this.infoNutricional = dto.getInfoNutricional();
        this.urlBlog = dto.getUrlBlog();
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

    public SimNao getProdutoComposto() {
        return produtoComposto;
    }

    public void setProdutoComposto(SimNao produtoComposto) {
        this.produtoComposto = produtoComposto;
    }

    public SimNao getUsoInterno() {
        return usoInterno;
    }

    public void setUsoInterno(SimNao usoInterno) {
        this.usoInterno = usoInterno;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<UnidadeMedida> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<UnidadeMedida> unidades) {
        this.unidades = unidades;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Integer qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public Integer getQtdMinimaEstoque() {
        return qtdMinimaEstoque;
    }

    public void setQtdMinimaEstoque(Integer qtdMinimaEstoque) {
        this.qtdMinimaEstoque = qtdMinimaEstoque;
    }

    public List<ProdutoComposicao> getComposicoes() {
        return composicoes;
    }

    public void setComposicoes(List<ProdutoComposicao> composicoes) {
        this.composicoes = composicoes;
    }

    public String getInfoComplementar() {
        return infoComplementar;
    }

    public void setInfoComplementar(String infoComplementar) {
        this.infoComplementar = infoComplementar;
    }

    public String getInfoNutricional() {
        return infoNutricional;
    }

    public void setInfoNutricional(String infoNutricional) {
        this.infoNutricional = infoNutricional;
    }

    public String getUrlBlog() {
        return urlBlog;
    }

    public void setUrlBlog(String urlBlog) {
        this.urlBlog = urlBlog;
    }

}