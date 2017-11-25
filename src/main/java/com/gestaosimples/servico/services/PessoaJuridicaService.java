package com.gestaosimples.servico.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.AuthorizationException;
import com.gestaosimples.arquitetura.exceptions.DataIntegrityException;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.arquitetura.security.UserSS;
import com.gestaosimples.arquitetura.services.auth.UserService;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.corp.PessoaJuridica;
import com.gestaosimples.servico.domain.enuns.PerfilEnum;
import com.gestaosimples.servico.repositories.EnderecoRepository;
import com.gestaosimples.servico.repositories.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public PessoaJuridica find(Long idPessoa) {

        UserSS user = UserService.getUsuarioLogado();
        if (user == null || !user.hasRole(PerfilEnum.A) && !idPessoa.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        PessoaJuridica obj = repo.findOne(idPessoa);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + idPessoa + ", Tipo: " + Cliente.class.getName());
        }
        return obj;
    }

    public PessoaJuridica insert(PessoaJuridica pj) {
        pj = repo.save(pj);
        enderecoRepository.save(pj.getEndereco());
        return pj;
    }

    public PessoaJuridica update(PessoaJuridica pj) {
        PessoaJuridica newObj = find(pj.getIdPessoa());
        updateData(newObj, pj);
        return repo.save(newObj);
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
        }
    }

    public List<PessoaJuridica> findAll() {
        return repo.findAll();
    }

    public Page<PessoaJuridica> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public PessoaJuridica fromDTO(Object objDto) {
        return null;
    }

    private void updateData(PessoaJuridica newObj, PessoaJuridica obj) {
        //newObj.setNome(obj.getNome());
        //newObj.setEmail(obj.getEmail());
    }
}
