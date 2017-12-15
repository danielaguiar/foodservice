package com.gestaosimples.servico.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.gestaosimples.corp.domain.UnidadeMedida;
import com.gestaosimples.servico.domain.Categoria;
import com.gestaosimples.servico.domain.Produto;
import com.gestaosimples.servico.domain.ProdutoComposicao;
import com.gestaosimples.servico.domain.enuns.SimNao;
import com.gestaosimples.servico.domain.enuns.Status;

public class ProdutoDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 6356730187701538408L;

    private Long idProduto;
    private Long idEmpresa;
    private String nmProduto;
    private SimNao produtoComposto;
    private SimNao usoInterno;
    private Status status;

    private Double preco;
    private Integer qtdMinimaEstoque;
    private Integer qtdMinima;

    private List<Categoria> categorias = new ArrayList<Categoria>();
    private List<UnidadeMedida> unidades = new ArrayList<UnidadeMedida>();
    private List<ProdutoComposicao> composicoes;

    private String infoComplementar;
    private String infoNutricional;
    private String urlBlog;

    public ProdutoDTO(Produto produto) {

        this.idProduto = produto.getId();
        this.idEmpresa = produto.getEmpresa().getId();
        this.nmProduto = produto.getNmProduto();
        this.produtoComposto = produto.getProdutoComposto();
        this.usoInterno = produto.getUsoInterno();
        this.status = produto.getStatus();
        this.categorias = produto.getCategorias();
        this.unidades = produto.getUnidades();
        this.preco = produto.getPreco();
        this.qtdMinima = produto.getQtdMinima();
        this.qtdMinimaEstoque = produto.getQtdMinima();
        this.composicoes = produto.getComposicoes();
        this.infoComplementar = produto.getInfoComplementar();
        this.infoNutricional = produto.getInfoNutricional();
        this.urlBlog = produto.getUrlBlog();
    }

    public ProdutoDTO(Long idProduto, Long idEmpresa, String nmProduto, SimNao possuiItens, SimNao produtoComposto, SimNao usoInterno, Status status,
        Double preco, Integer qtdMinimaEstoque, Integer qtdMinima, List<Categoria> categorias, List<UnidadeMedida> unidades,
        List<ProdutoComposicao> composicoes, String infoComplementar, String infoNutricional, String urlBlog) {
        super();
        this.idProduto = idProduto;
        this.idEmpresa = idEmpresa;
        this.nmProduto = nmProduto;
        this.produtoComposto = produtoComposto;
        this.usoInterno = usoInterno;
        this.status = status;
        this.qtdMinimaEstoque = qtdMinimaEstoque;
        this.qtdMinima = qtdMinima;
        this.categorias = categorias;
        this.unidades = unidades;
        this.composicoes = composicoes;
        this.infoComplementar = infoComplementar;
        this.infoNutricional = infoNutricional;
        this.urlBlog = urlBlog;
    }

    public ProdutoDTO(Long idProduto, Long idEmpresa, String nmProduto, SimNao produtoComposto, SimNao usoInterno, Status status, Double preco) {
        super();
        this.idProduto = idProduto;
        this.idEmpresa = idEmpresa;
        this.nmProduto = nmProduto;
        this.produtoComposto = produtoComposto;
        this.usoInterno = usoInterno;
        this.status = status;
        this.preco = preco;
        //        this.qtdMinimaEstoque = qtdMinimaEstoque;
        //        this.qtdMinima = qtdMinima;
        //        this.categorias = categorias;
        //        this.unidades = unidades;
        //        this.composicoes = composicoes;
        //        this.infoComplementar = infoComplementar;
        //        this.infoNutricional = infoNutricional;
        //        this.urlBlog = urlBlog;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNmProduto() {
        return nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public Integer getQtdMinimaEstoque() {
        return qtdMinimaEstoque;
    }

    public void setQtdMinimaEstoque(Integer qtdMinimaEstoque) {
        this.qtdMinimaEstoque = qtdMinimaEstoque;
    }

    public Integer getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Integer qtdMinima) {
        this.qtdMinima = qtdMinima;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
