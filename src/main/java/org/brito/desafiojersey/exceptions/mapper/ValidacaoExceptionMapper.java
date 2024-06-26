package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.brito.desafiojersey.exceptions.AutenticacaoException;
import org.brito.desafiojersey.exceptions.ErrorResponse;

public class ValidacaoExceptionMapper implements ExceptionMapper<AutenticacaoException> {

    @Override
    public Response toResponse(AutenticacaoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                Response.Status.UNAUTHORIZED.getStatusCode(),
                ex.getMessage()
        );

        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
