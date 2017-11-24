package com.gestaosimples.servico.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestaosimples.arquitetura.exceptions.AutorizationException;
import com.gestaosimples.arquitetura.exceptions.DataIntegrityException;
import com.gestaosimples.arquitetura.exceptions.ObjectNotFoundException;
import com.gestaosimples.arquitetura.security.UserSS;
import com.gestaosimples.servico.domain.Empresa;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;
import com.gestaosimples.servico.domain.enuns.PerfilEnum;
import com.gestaosimples.servico.repositories.EmpresaRepository;
import com.gestaosimples.servico.repositories.EnderecoRepository;
import com.gestaosimples.servico.services.auth.UserService;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public Empresa find(Long id) {

        UserSS user = UserService.authenticated();
        if (user != null && !user.hasRole(PerfilEnum.A) && !user.getId().equals(id)) {
            throw new AutorizationException("operação no permitida");
        }

        Empresa obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + EmpresaService.class.getName());
        }
        return obj;
    }

    public Empresa insert(Empresa empresa) {
        empresa.setPessoa(null);
        Empresa cli = repo.save(empresa);
        //enderecoRepository.save(empresa.getEnderecos());
        return cli;
    }

    public Empresa update(Empresa empresa) {
        Empresa cli = find(empresa.getPessoa().getIdPessoa());
        updataData(empresa, cli);
        return repo.save(empresa);
    }

    private void updataData(Empresa empresa, Empresa cli) {
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma empresa que tem pedidos");
        }
    }

    public List<Empresa> findAll() {
        return repo.findAll();
    }

    public Page<Empresa> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        return repo.findAll(pageRequest);
    }

    public Empresa fromDTO(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        /*
         * Endereco end = new Endereco(dto.getLogradouro(), dto.getNumero(), dto.getComplemento(),
         * dto.getBairro(), dto.getCep(), new Pessoa(1l), new Cidade( dto.getIdCidade()));
         * cli.getEnderecos().add(end); cli.getTelefones().add(dto.getTelefone1()); if
         * (!ObjetoUtil.isVazio(dto.getTelefone2())) { cli.getTelefones().add(dto.getTelefone2());
         * } if (!ObjetoUtil.isVazio(dto.getTelefone3())) {
         * cli.getTelefones().add(dto.getTelefone3()); } cli.setSenha(pe.encode(dto.getSenha()));
         */
        return empresa;
    }
}
