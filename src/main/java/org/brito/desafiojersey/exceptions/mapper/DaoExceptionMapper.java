package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.brito.desafiojersey.exceptions.DaoException;

@Provider
public class DaoExceptionMapper implements ExceptionMapper<DaoException> {

    @Override
    public Response toResponse(DaoException ex) {
        return new ExceptionBuilder(
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
