package com.gestaosimples.servico.validation.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.gestaosimples.arquitetura.util.CPFCNPJUtil;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.arquitetura.validation.FieldMessage;
import com.gestaosimples.corp.domain.Email;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;
import com.gestaosimples.servico.validation.AbstractValitation;
import com.gestaosimples.servico.validation.EmpresaValidation;

public class EmpresaValidator extends AbstractValitation implements ConstraintValidator<EmpresaValidation, EmpresaDTO> {

    @Autowired
    HttpServletRequest request;

    @Override
    public void initialize(EmpresaValidation arg) {

    }

    @Override
    public boolean isValid(EmpresaDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        Long idEmpresa = recuperarIdURL("id");

        validarEmpresa(idEmpresa, dto, list);
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }

    private void validarEmpresa(Long idEmpresa, EmpresaDTO dto, List<FieldMessage> list) {
        if (ObjetoUtil.isVazio(dto.getNmRazaoSocial())) {
            list.add(new FieldMessage("nmRazaoSocial", "O campo Razão Social é obrigatório"));
        }

        if (!ObjetoUtil.isVazio(idEmpresa)) {
            Email email = emailRepository.findByEdEmail(dto.getEmail().getEdEmail());
            if (email != null && !email.getId().equals(dto.getEmail().getId())) {
                list.add(new FieldMessage("email", "O email já está sendo utilizado por outro usuário"));
            }
        } else {
            if (ObjetoUtil.isVazio(dto.getNrCnpj())) {
                list.add(new FieldMessage("nmRazaoSocial", "O campo CNPJ é obrigatório"));
            } else if (!CPFCNPJUtil.isCnpj(dto.getNrCnpj())) {
                list.add(new FieldMessage("nmRazaoSocial", "O CNPJ está inválido"));
            } else if (empresaService.isCNPJUtilizado(dto.getNrCnpj())) {
                list.add(new FieldMessage("nrCnpj", "O CNPJ " + dto.getNrCnpj() + " já está sendo utilizado"));
            }
            if (ObjetoUtil.isVazio(dto.getEmail())) {
                list.add(new FieldMessage("email", "O campo email é obrigatório"));
            } else if (dto.getEmail() != null && empresaService.isEmailUtilizado(dto.getEmail().getEdEmail())) {
                list.add(new FieldMessage("email", "O e-mail " + dto.getEmail().getEdEmail() + " já está sendo utilizado"));
            }
        }

        if (ObjetoUtil.isVazio(dto.getEndereco())) {
            list.add(new FieldMessage("endereco", "O campo endereco é obrigatório"));
        }
        if (ObjetoUtil.isVazio(dto.getTelefone())) {
            list.add(new FieldMessage("telefone", "O campo telefone é obrigatório"));
        }
    }
}
