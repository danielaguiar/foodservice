package com.gestaosimples.servico.resources;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;
import com.gestaosimples.servico.services.EmpresaService;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaResource extends AbstractResource {

    @Autowired
    private EmpresaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id) {
        EmpresaDTO empresa = service.find(id);
        return this.okResponseObject(empresa);

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<?>> findAll() {
        List<EmpresaDTO> empresas = service.findAll();
        return this.okResponseList(empresas);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<EmpresaDTO> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, //
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, //
        @RequestParam(value = "orderby", defaultValue = "nmRazaoSocial") String orderby, //
        @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<EmpresaDTO> lista = service.findPage(page, linesPerPage, orderby, direction);
        return lista;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody EmpresaDTO empresa) {
        EmpresaDTO obj = service.insert(service.fromDTO(empresa));
        return this.createResponse("/{id}", obj.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody EmpresaDTO empresa) {
        service.update(empresa);
        return this.noContentResponse();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return this.noContentResponse();

    }

}
