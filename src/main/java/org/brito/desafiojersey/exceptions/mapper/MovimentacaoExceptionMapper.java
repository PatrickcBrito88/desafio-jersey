package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.brito.desafiojersey.exceptions.MovimentacaoException;

public class MovimentacaoExceptionMapper implements ExceptionMapper<MovimentacaoException> {

    @Override
    public Response toResponse(MovimentacaoException ex) {
        return new ExceptionBuilder(
                Response.Status.BAD_REQUEST.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
