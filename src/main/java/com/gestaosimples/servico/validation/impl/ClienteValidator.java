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
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.repositories.EmailRepository;
import com.gestaosimples.servico.repositories.PessoaFisicaRepository;
import com.gestaosimples.servico.validation.ClienteValidation;

public class ClienteValidator implements ConstraintValidator<ClienteValidation, ClienteDTO> {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private PessoaFisicaRepository repo;

    @Autowired
    private EmailRepository EmailRepository;

    @Override
    public void initialize(ClienteValidation arg) {

    }

    @Override
    public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
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

    private void insertValidation(ClienteDTO dto, List<FieldMessage> list) {

    }

    private void updateValidation(ClienteDTO dto, List<FieldMessage> list) {

    }

}
