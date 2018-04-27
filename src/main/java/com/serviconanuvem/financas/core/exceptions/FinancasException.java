package com.serviconanuvem.financas.core.exceptions;

public class FinancasException extends RuntimeException {

    public FinancasException(String message) {
        super(message);
    }

    public FinancasException(String message, Throwable cause) {
        super(message, cause);
    }

    public FinancasException(Throwable cause) {
        super(cause);
    }

    protected FinancasException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
