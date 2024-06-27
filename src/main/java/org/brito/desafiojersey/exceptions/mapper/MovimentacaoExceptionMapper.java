package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.brito.desafiojersey.exceptions.MovimentacaoException;

@Provider
public class MovimentacaoExceptionMapper implements ExceptionMapper<MovimentacaoException> {

    @Override
    public Response toResponse(MovimentacaoException ex) {
        return new ExceptionBuilder(
                Response.Status.BAD_REQUEST.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
