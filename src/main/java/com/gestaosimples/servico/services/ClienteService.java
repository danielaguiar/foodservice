package com.gestaosimples.servico.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.DataIntegrityException;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.servico.domain.corp.PessoaFisica;
import com.gestaosimples.servico.domain.corp.PessoaJuridica;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.repositories.PessoaFisicaRepository;

@Service
public class ClienteService {

    @Autowired
    private PessoaFisicaRepository repo;

    private PessoaFisica findOne(Long id) {
        PessoaFisica pessoa = repo.findOne(id);
        if (pessoa == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + PessoaJuridica.class.getName());
        }
        return pessoa;
    }

    public ClienteDTO find(Long id) {

        //UserSS user = UserService.getUsuarioLogado();
        //if (UserService.isUsuarioLogadoAdmin() && !user.getId().equals(id)) {
        //    throw new AuthorizationException("operação no permitida");
        //}

        PessoaFisica pessoa = findOne(id);
        return fromPessoaFisica(pessoa);
    }

    public ClienteDTO insert(PessoaFisica pessoa) {
        PessoaFisica pessoaInserida = repo.save(pessoa);
        return fromPessoaFisica(pessoaInserida);
    }

    public ClienteDTO update(ClienteDTO pessoaDTO) {
        PessoaFisica empresaBanco = findOne(pessoaDTO.getId());
        PessoaFisica pessoa = fromDTO(pessoaDTO);
        updataData(pessoa, empresaBanco);
        repo.save(empresaBanco);
        return fromPessoaFisica(empresaBanco);
    }

    private void updataData(PessoaFisica pessoa, PessoaFisica pessoaBanco) {
        //pessoaBanco.setNome(pessoaAtual.getNome());
        //pessoaBanco.setEmail(pessoaAtual.getEmail());
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cliente que tem pedidos");
        }
    }

    public List<ClienteDTO> findAll() {
        List<PessoaFisica> findAll = repo.findAll();
        return findAll.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
    }

    public Page<ClienteDTO> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        Page<PessoaFisica> findAll = repo.findAll(pageRequest);
        return findAll.map(obj -> new ClienteDTO(obj));
    }

    private ClienteDTO fromPessoaFisica(PessoaFisica pessoa) {
        return new ClienteDTO(pessoa);
    }

    public PessoaFisica fromDTO(ClienteDTO dto) {
        return new PessoaFisica(dto);
    }
}
