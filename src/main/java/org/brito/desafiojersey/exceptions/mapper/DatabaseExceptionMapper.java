package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.brito.desafiojersey.exceptions.DatabaseException;
import org.brito.desafiojersey.exceptions.ErrorResponse;

public class DatabaseExceptionMapper implements ExceptionMapper<DatabaseException> {

    @Override
    public Response toResponse(DatabaseException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                ex.getMessage()
        );

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
