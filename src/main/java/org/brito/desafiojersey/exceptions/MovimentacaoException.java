package org.brito.desafiojersey.exceptions;

public class MovimentacaoException extends RuntimeException {

    public MovimentacaoException(String message) {
        super(message);
    }

    public MovimentacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}