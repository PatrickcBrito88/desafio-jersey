package org.brito.desafiojersey.exceptions;

public class ConteinerException extends RuntimeException {

    public ConteinerException(String message) {
        super(message);
    }

    public ConteinerException(String message, Throwable cause) {
        super(message, cause);
    }
}