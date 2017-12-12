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
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource extends AbstractResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id) {
        ClienteDTO cliente = service.find(id);
        return this.okResponseObject(cliente);

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<?>> findAll() {
        List<ClienteDTO> clientes = service.findAll();
        return this.okResponseList(clientes);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<ClienteDTO> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, //
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, //
        @RequestParam(value = "orderby", defaultValue = "nmPessoaFisica") String orderby, //
        @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<ClienteDTO> listaDTO = service.findPage(page, linesPerPage, orderby, direction);
        return listaDTO;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO cliente) {
        ClienteDTO obj = service.insert(service.fromDTO(cliente));
        return this.createResponse("/{id}", obj.getIdCliente());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Cliente cateogria) {
        return this.noContentResponse();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return this.noContentResponse();

    }

}
