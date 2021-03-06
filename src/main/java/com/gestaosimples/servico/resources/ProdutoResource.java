package com.gestaosimples.servico.resources;

import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gestaosimples.servico.domain.dto.ProdutoDTO;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource extends AbstractResource {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id) {
        ProdutoDTO produto = produtoService.find(id);
        return this.okResponseObject(produto);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN_MASTER')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<?>> findAll() {
        List<ProdutoDTO> produtos = produtoService.findAll();
        return this.okResponseList(produtos);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN_MASTER')")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<ProdutoDTO> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, //
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, //
        @RequestParam(value = "orderby", defaultValue = "produto.nmRazaoSocial") String orderby, //
        @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<ProdutoDTO> lista = produtoService.findPage(page, linesPerPage, orderby, direction);
        return lista;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoDTO produto) {
        ProdutoDTO obj = produtoService.insert(produtoService.fromDTO(produto));
        return this.createResponse("/{id}", obj.getIdEmpresa());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produto) {
        produto.setIdEmpresa(id);
        produtoService.update(produto);
        return this.noContentResponse();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return this.noContentResponse();

    }

}
