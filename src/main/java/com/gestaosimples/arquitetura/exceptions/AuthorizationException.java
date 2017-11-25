package com.gestaosimples.arquitetura.exceptions;

public class AuthorizationException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = -1296816611266996518L;

    public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
