package com.gestaosimples.servico.validation.impl;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import com.gestaosimples.arquitetura.validation.FieldMessage;
import com.gestaosimples.servico.domain.dto.ClienteDTO;
import com.gestaosimples.servico.validation.AbstractValitation;
import com.gestaosimples.servico.validation.ClienteValidation;

public class ClienteValidator extends AbstractValitation implements ConstraintValidator<ClienteValidation, ClienteDTO> {

    @Override
    public void initialize(ClienteValidation arg) {

    }

    @Override
    public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        Long idCliente = recuperarIdURL("id");

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

    private void insertValidation(ClienteDTO dto, List<FieldMessage> list) {

    }

    private void updateValidation(ClienteDTO dto, List<FieldMessage> list) {

    }

}
