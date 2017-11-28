package com.gestaosimples.servico.services;

import java.util.List;
import java.util.stream.Collectors;
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
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.servico.domain.corp.PessoaJuridica;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;
import com.gestaosimples.servico.repositories.EmpresaRepository;
import com.gestaosimples.servico.repositories.EnderecoRepository;

@Service
public class EmpresaService extends AbstractService {

    @Autowired
    private EmpresaRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private PessoaJuridica findOne(Long id) {
        PessoaJuridica pessoa = repo.findOne(id);
        if (pessoa == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + PessoaJuridica.class.getName());
        }
        return pessoa;
    }

    public EmpresaDTO find(Long id) {

        UserSS user = UserService.getUsuarioLogado();
        if (!UserService.isUsuarioLogadoAdminMaster() && !user.getId().equals(id)) {
            throw new AuthorizationException("operação no permitida");
        }

        PessoaJuridica pessoa = findOne(id);
        return fromPessoaJuridica(pessoa);
    }

    public EmpresaDTO insert(PessoaJuridica pessoa) {
        PessoaJuridica pessoaInserida = repo.save(pessoa);
        return fromPessoaJuridica(pessoaInserida);
    }

    public EmpresaDTO update(EmpresaDTO pessoaDTO) {
        PessoaJuridica empresaBanco = findOne(pessoaDTO.getId());
        PessoaJuridica pessoa = fromDTO(pessoaDTO);
        updataData(pessoa, empresaBanco);
        repo.save(empresaBanco);
        return fromPessoaJuridica(empresaBanco);
    }

    private void updataData(PessoaJuridica pessoa, PessoaJuridica pessoaBanco) {
        pessoaBanco.setNmFantasia(pessoa.getNmFantasia());
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cliente que tem pedidos");
        }
    }

    public List<EmpresaDTO> findAll() {
        List<PessoaJuridica> findAll = repo.findAll();
        List<EmpresaDTO> empresas = findAll.stream().map(x -> new EmpresaDTO(x)).collect(Collectors.toList());
        return empresas;
    }

    public Page<EmpresaDTO> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        Page<PessoaJuridica> findAll = repo.findAll(pageRequest);
        return findAll.map(obj -> new EmpresaDTO(obj));
    }

    private EmpresaDTO fromPessoaJuridica(PessoaJuridica pessoa) {
        return new EmpresaDTO(pessoa);
    }

    public PessoaJuridica fromDTO(EmpresaDTO dto) {
        return new PessoaJuridica(dto);
    }

    public boolean isCNPJUtilizado(String nrCnpj) {
        PessoaJuridica pessoa = repo.findByNrCnpj(nrCnpj);
        return !ObjetoUtil.isVazio(pessoa);
    }

    public boolean isEmailUtilizado(String email) {
        PessoaJuridica pessoa = repo.findByEmailEdEmail(email);
        return !ObjetoUtil.isVazio(pessoa);
    }
}
