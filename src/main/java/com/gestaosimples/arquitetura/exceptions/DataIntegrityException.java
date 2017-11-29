package com.gestaosimples.arquitetura.exceptions;

public class DataIntegrityException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = -1296816611266996518L;

    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
