package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.brito.desafiojersey.exceptions.ConflitoException;
import org.brito.desafiojersey.exceptions.ErrorResponse;

public class ConflitoExceptionMapper implements ExceptionMapper<ConflitoException> {

    @Override
    public Response toResponse(ConflitoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                Response.Status.CONFLICT.getStatusCode(),
                ex.getMessage()
        );

        return Response
                .status(Response.Status.CONFLICT)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
