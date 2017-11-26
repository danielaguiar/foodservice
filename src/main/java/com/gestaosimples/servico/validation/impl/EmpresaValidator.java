package com.gestaosimples.servico.validation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.arquitetura.validation.FieldMessage;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.dto.EmpresaDTO;
import com.gestaosimples.servico.repositories.PessoaFisicaRepository;
import com.gestaosimples.servico.validation.EmpresaValidation;

public class EmpresaValidator implements ConstraintValidator<EmpresaValidation, EmpresaDTO> {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private PessoaFisicaRepository repo;

    @Override
    public void initialize(EmpresaValidation arg) {

    }

    @Override
    public boolean isValid(EmpresaDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        Long idCliente = recuperarIdCliente();

        if (ObjetoUtil.isVazio(idCliente)) {
            insertValidation(dto, list);
        } else {
            updateValidation(dto, list);

        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }

    @SuppressWarnings("unchecked")
    private Long recuperarIdCliente() {
        try {
            Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            return Long.parseLong(map.get("id"));
        } catch (Exception e) {
            return null;
        }
    }

    private void insertValidation(EmpresaDTO dto, List<FieldMessage> list) {
        //if (dto.isPessoaFisica() && !CPFCNPJUtil.isValidarCpfCnpj(dto.getCpfOuCnpj())) {
        //    list.add(new FieldMessage("cpfOuCnpj", CPFCNPJUtil.isCpf(dto.getCpfOuCnpj()) ? "CPF inválido" : "CNPJ inválido"));
        //}

        validarEmail(dto, list);
    }

    private void updateValidation(EmpresaDTO dto, List<FieldMessage> list) {

    }

    private void validarEmail(EmpresaDTO dto, List<FieldMessage> list) {
        Cliente aux = null;//repo.findByEmail(dto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }
    }

}
