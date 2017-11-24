package com.gestaosimples.servico.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gestaosimples.arquitetura.util.StringUtil;
import com.gestaosimples.servico.domain.Produto;
import com.gestaosimples.servico.domain.dto.ProdutoDTO;
import com.gestaosimples.servico.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Long id) {
        Produto produto = service.find(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<ProdutoDTO> findPage(@RequestParam(value = "nome", defaultValue = "") String nome, //
        @RequestParam(value = "categorias", defaultValue = "") String categorias, //
        @RequestParam(value = "page", defaultValue = "0") Integer page, //
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, //
        @RequestParam(value = "orderby", defaultValue = "nome") String orderby, //
        @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Produto> produtos = service.findPage(nome, StringUtil.decodeInt(categorias), page, linesPerPage, orderby, direction);
        Page<ProdutoDTO> pageProdutos = produtos.map(prod -> new ProdutoDTO(prod));
        return pageProdutos;
    }
}
