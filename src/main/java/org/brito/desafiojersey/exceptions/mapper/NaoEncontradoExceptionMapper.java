package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.brito.desafiojersey.exceptions.ErrorResponse;
import org.brito.desafiojersey.exceptions.NaoEncontradoException;

public class NaoEncontradoExceptionMapper implements ExceptionMapper<NaoEncontradoException> {

    @Override
    public Response toResponse(NaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                Response.Status.NOT_FOUND.getStatusCode(),
                ex.getMessage()
        );

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
