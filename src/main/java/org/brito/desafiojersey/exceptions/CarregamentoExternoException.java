package org.brito.desafiojersey.exceptions;

public class CarregamentoExternoException extends RuntimeException {

    public CarregamentoExternoException(String message) {
        super(message);
    }

    public CarregamentoExternoException(String message, Throwable cause) {
        super(message, cause);
    }
}