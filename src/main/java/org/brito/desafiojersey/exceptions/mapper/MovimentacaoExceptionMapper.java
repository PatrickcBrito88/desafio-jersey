package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.brito.desafiojersey.exceptions.ErrorResponse;
import org.brito.desafiojersey.exceptions.MovimentacaoException;

public class MovimentacaoExceptionMapper implements ExceptionMapper<MovimentacaoException> {

    @Override
    public Response toResponse(MovimentacaoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                Response.Status.BAD_REQUEST.getStatusCode(),
                ex.getMessage()
        );

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
