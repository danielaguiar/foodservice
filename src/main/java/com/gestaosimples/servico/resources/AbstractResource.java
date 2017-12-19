package com.gestaosimples.servico.resources;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gestaosimples.servico.services.AbstractService;

public abstract class AbstractResource extends AbstractService {

    protected URI getUriPath(String path, Object id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(id).toUri();
        return uri;
    }

    protected ResponseEntity<Void> noContentResponse() {
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<Void> createResponse(String path, Object id) {
        return ResponseEntity.created(this.getUriPath("/{id}", id)).build();
    }

    protected ResponseEntity<List<?>> okResponseList(List<?> lista) {
        return ResponseEntity.ok().body(lista);
    }

    protected ResponseEntity<?> okResponseObject(Object obj) {
        return ResponseEntity.ok().body(obj);
    }

}
