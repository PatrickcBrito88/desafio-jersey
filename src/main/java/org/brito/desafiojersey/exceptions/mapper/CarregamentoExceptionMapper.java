package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.brito.desafiojersey.exceptions.CarregamentoExternoException;

@Provider
public class CarregamentoExceptionMapper implements ExceptionMapper<CarregamentoExternoException> {

    @Override
    public Response toResponse(CarregamentoExternoException ex) {
        return new ExceptionBuilder(
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
