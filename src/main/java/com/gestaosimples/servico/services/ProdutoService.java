package com.gestaosimples.servico.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.AuthorizationException;
import com.gestaosimples.arquitetura.exceptions.DataIntegrityException;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.arquitetura.security.services.UserService;
import com.gestaosimples.arquitetura.security.util.UserSS;
import com.gestaosimples.servico.domain.Produto;
import com.gestaosimples.servico.domain.dto.ProdutoDTO;

@Service
public class ProdutoService extends AbstractRepository {

    private Produto findOne(Long id) {
        Produto pessoa = produtoRepository.findOne(id);
        if (pessoa == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + Produto.class.getName());
        }
        return pessoa;
    }

    public ProdutoDTO find(Long id) {

        UserSS user = UserService.getUsuarioLogado();
        if (!UserService.isUsuarioLogadoAdminMaster() && !user.getId().equals(id)) {
            throw new AuthorizationException("operação no permitida");
        }

        Produto produto = findOne(id);
        return fromProduto(produto);
    }

    public ProdutoDTO insert(Produto produto) {

        Produto pessoaInserida = produtoRepository.save(produto);
        return fromProduto(pessoaInserida);
    }

    public ProdutoDTO update(ProdutoDTO produtoDTO) {
        Produto produtoBanco = findOne(produtoDTO.getIdProduto());
        Produto pessoa = fromDTO(produtoDTO);
        updataData(pessoa, produtoBanco);
        produtoRepository.save(produtoBanco);
        return fromProduto(produtoBanco);
    }

    private void updataData(Produto pessoa, Produto pessoaBanco) {
        //pessoaBanco.setNmFantasia(pessoa.getNmFantasia());
    }

    public void delete(Long id) {
        find(id);
        try {
            produtoRepository.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cliente que tem pedidos");
        }
    }

    public List<ProdutoDTO> findAll() {
        List<Produto> findAll = produtoRepository.findAll();
        List<ProdutoDTO> produtos = findAll.stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
        return produtos;
    }

    public Page<ProdutoDTO> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        Page<Produto> findAll = produtoRepository.findAll(pageRequest);
        return findAll.map(obj -> new ProdutoDTO(obj));
    }

    private ProdutoDTO fromProduto(Produto pessoa) {
        return new ProdutoDTO(pessoa);
    }

    public Produto fromDTO(ProdutoDTO dto) {
        return new Produto(dto);
    }
}
