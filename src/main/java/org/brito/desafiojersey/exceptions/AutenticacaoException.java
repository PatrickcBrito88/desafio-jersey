package org.brito.desafiojersey.exceptions;

public class AutenticacaoException extends RuntimeException {

    public AutenticacaoException(String message) {
        super(message);
    }

    public AutenticacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}