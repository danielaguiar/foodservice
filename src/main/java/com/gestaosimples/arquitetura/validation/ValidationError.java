package com.gestaosimples.arquitetura.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

public class ValidationError extends StandardError implements Serializable {

    /**  */
    private static final long serialVersionUID = -8061761562071215986L;

    private List<FieldMessage> errors = new ArrayList<FieldMessage>();

    public ValidationError(HttpStatus status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String filedName, String message) {
        this.errors.add(new FieldMessage(filedName, message));
    }

}
