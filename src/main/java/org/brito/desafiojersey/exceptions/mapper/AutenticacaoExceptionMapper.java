package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.brito.desafiojersey.exceptions.AutenticacaoException;

public class AutenticacaoExceptionMapper implements ExceptionMapper<AutenticacaoException> {

    @Override
    public Response toResponse(AutenticacaoException ex) {
        return new ExceptionBuilder(
                Response.Status.UNAUTHORIZED.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
