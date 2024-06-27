package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.brito.desafiojersey.exceptions.DatabaseException;

@Provider
public class DatabaseExceptionMapper implements ExceptionMapper<DatabaseException> {

    @Override
    public Response toResponse(DatabaseException ex) {
        return new ExceptionBuilder(
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
