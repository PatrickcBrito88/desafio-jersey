package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.brito.desafiojersey.exceptions.ConteinerException;

@Provider
public class ConteinerExceptionMapper implements ExceptionMapper<ConteinerException> {

    @Override
    public Response toResponse(ConteinerException ex) {
        return new ExceptionBuilder(
                Response.Status.BAD_REQUEST.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
