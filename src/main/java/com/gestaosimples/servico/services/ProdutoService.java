package com.gestaosimples.servico.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.servico.domain.Categoria;
import com.gestaosimples.servico.domain.Produto;
import com.gestaosimples.servico.repositories.CategoriaRepository;
import com.gestaosimples.servico.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Long id) {
        Produto obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + ProdutoService.class.getName());
        }
        return obj;
    }

    public Page<Produto> findPage(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        List<Categoria> categorias = categoriaRepository.findAll(ids);
        if (!ObjetoUtil.isVazio(categorias)) {
            return repo.findDistinctByNomeContainingIgnoreCaseAndCategoriasIn(nome, categorias, pageRequest);
        } else {
            return repo.findDistinctByNomeContainingIgnoreCase(nome, pageRequest);
        }
    }
}
