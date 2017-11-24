package com.gestaosimples.servico.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.DataIntegrityException;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.servico.domain.Categoria;
import com.gestaosimples.servico.domain.dto.CategoriaDTO;
import com.gestaosimples.servico.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Long id) {
        Categoria obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + CategoriaService.class.getName());
        }
        return obj;
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repo.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        Categoria cat = find(categoria.getId());
        updataData(categoria, cat);
        return repo.save(cat);
    }

    private void updataData(Categoria categoria, Categoria cat) {
        cat.setNome(categoria.getNome());
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um cliente que possua pedidos");
        }
    }

    public List<Categoria> findAll() {
        return repo.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        return repo.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO dto) {
        return new Categoria(dto.getId(), dto.getNome());
    }
}
