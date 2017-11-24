package com.gestaosimples.servico.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id) {
        Cliente cliente = service.find(id);
        cliente.getPerfis();
        return ResponseEntity.ok().body(cliente);

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> clientes = service.findAll();
        List<ClienteDTO> listDto = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<ClienteDTO> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, //
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, //
        @RequestParam(value = "orderby", defaultValue = "id") String orderby, //
        @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Cliente> lista = service.findPage(page, linesPerPage, orderby, direction);
        Page<ClienteDTO> listDto = lista.map(obj -> new ClienteDTO(obj));
        return listDto;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO cateogria) {
        Cliente obj = service.insert(service.fromDTO(cateogria));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Cliente cateogria) {
        cateogria.setId(id);
        service.update(cateogria);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}
