package com.gestaosimples.servico.resources.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.gestaosimples.arquitetura.util.CPFCNPJUtil;
import com.gestaosimples.arquitetura.validation.FieldMessage;
import com.gestaosimples.servico.domain.Cliente;
import com.gestaosimples.servico.domain.dto.ClienteNewDTO;
import com.gestaosimples.servico.repositories.ClienteRepository;

public class ClienteValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.isPessoaFisica() && !CPFCNPJUtil.isValidarCpfCnpj(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", CPFCNPJUtil.isCpf(objDto.getCpfOuCnpj()) ? "CPF inválido" : "CNPJ inválido"));
        }

        updateValidation(objDto, list);

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }

    private void updateValidation(ClienteNewDTO objDto, List<FieldMessage> list) {
        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }
    }
}