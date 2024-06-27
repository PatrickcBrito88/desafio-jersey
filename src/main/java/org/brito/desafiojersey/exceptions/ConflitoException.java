package org.brito.desafiojersey.exceptions;

public class ConflitoException extends RuntimeException {

    public ConflitoException(String message) {
        super(message);
    }

    public ConflitoException(String message, Throwable cause) {
        super(message, cause);
    }
}