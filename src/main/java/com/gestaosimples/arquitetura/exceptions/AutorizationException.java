package com.gestaosimples.arquitetura.exceptions;

public class AutorizationException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = -1296816611266996518L;

    public AutorizationException(String msg) {
        super(msg);
    }

    public AutorizationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
