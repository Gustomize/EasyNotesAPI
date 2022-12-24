package com.gsilva.easynotes.exception;

public class NotaNaoEncontradaException extends RuntimeException {

    public NotaNaoEncontradaException() {
    }

    public NotaNaoEncontradaException(String message) {
        super(message);
    }

    public NotaNaoEncontradaException(Throwable cause) {
        super(cause);
    }

    public NotaNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotaNaoEncontradaException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
