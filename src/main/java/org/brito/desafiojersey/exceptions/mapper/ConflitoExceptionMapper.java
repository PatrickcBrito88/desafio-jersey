package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.brito.desafiojersey.exceptions.ConflitoException;

public class ConflitoExceptionMapper implements ExceptionMapper<ConflitoException> {

    @Override
    public Response toResponse(ConflitoException ex) {
        return new ExceptionBuilder(
                Response.Status.CONFLICT.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
