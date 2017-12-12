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
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.servico.domain.Empresa;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;

@Service
public class EmpresaService extends AbstractRepository {

    private Empresa findOne(Long id) {
        Empresa pessoa = empresaRepository.findOne(id);
        if (pessoa == null) {
            throw new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", " + Empresa.class.getName());
        }
        return pessoa;
    }

    public EmpresaDTO find(Long id) {

        UserSS user = UserService.getUsuarioLogado();
        if (!UserService.isUsuarioLogadoAdminMaster() && !user.getId().equals(id)) {
            throw new AuthorizationException("operação no permitida");
        }

        Empresa pessoa = findOne(id);
        return fromEmpresa(pessoa);
    }

    public EmpresaDTO insert(Empresa empresa) {

        pessoaJuridicaRepository.save(empresa.getEmpresa());
        enderecoRepository.save(empresa.getEndereco());
        telefoneRepository.save(empresa.getTelefone());
        emailRepository.save(empresa.getEmail());
        Empresa pessoaInserida = empresaRepository.save(empresa);
        return fromEmpresa(pessoaInserida);
    }

    public EmpresaDTO update(EmpresaDTO pessoaDTO) {
        Empresa empresaBanco = findOne(pessoaDTO.getIdEmpresa());
        Empresa pessoa = fromDTO(pessoaDTO);
        updataData(pessoa, empresaBanco);
        empresaRepository.save(empresaBanco);
        return fromEmpresa(empresaBanco);
    }

    private void updataData(Empresa pessoa, Empresa pessoaBanco) {
        //pessoaBanco.setNmFantasia(pessoa.getNmFantasia());
    }

    public void delete(Long id) {
        find(id);
        try {
            empresaRepository.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cliente que tem pedidos");
        }
    }

    public List<EmpresaDTO> findAll() {
        List<Empresa> findAll = empresaRepository.findAll();
        List<EmpresaDTO> empresas = findAll.stream().map(x -> new EmpresaDTO(x)).collect(Collectors.toList());
        return empresas;
    }

    public Page<EmpresaDTO> findPage(Integer page, Integer linesPerPage, String orderby, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderby);
        Page<Empresa> findAll = empresaRepository.findAll(pageRequest);
        return findAll.map(obj -> new EmpresaDTO(obj));
    }

    private EmpresaDTO fromEmpresa(Empresa pessoa) {
        return new EmpresaDTO(pessoa);
    }

    public Empresa fromDTO(EmpresaDTO dto) {
        return new Empresa(dto);
    }

    public boolean isCNPJUtilizado(String nrCnpj) {
        Empresa empresa = empresaRepository.findByEmpresaNrCnpj(nrCnpj);
        return !ObjetoUtil.isVazio(empresa);
    }

    public boolean isEmailUtilizado(String email) {
        Empresa pessoa = empresaRepository.findByEmailEdEmail(email);
        return !ObjetoUtil.isVazio(pessoa);
    }
}
