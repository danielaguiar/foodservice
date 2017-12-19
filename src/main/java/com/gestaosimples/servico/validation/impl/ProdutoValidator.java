package com.gestaosimples.servico.validation.impl;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.gestaosimples.arquitetura.validation.FieldMessage;
import com.gestaosimples.servico.domain.dto.ProdutoDTO;
import com.gestaosimples.servico.validation.AbstractValitation;
import com.gestaosimples.servico.validation.ProdutoValidation;

public class ProdutoValidator extends AbstractValitation implements ConstraintValidator<ProdutoValidation, ProdutoDTO> {

    @Override
    public void initialize(ProdutoValidation arg) {

    }

    @Override
    public boolean isValid(ProdutoDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        Long idEmpresa = recuperarIdURL("id");

        validarProduto(idEmpresa, dto, list);
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }

    private void validarProduto(Long idEmpresa, ProdutoDTO dto, List<FieldMessage> list) {

    }
}
