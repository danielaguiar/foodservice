package com.gestaosimples.arquitetura.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = -1296816611266996518L;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
