package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.brito.desafiojersey.exceptions.NaoEncontradoException;

@Provider
public class NaoEncontradoExceptionMapper implements ExceptionMapper<NaoEncontradoException> {

    @Override
    public Response toResponse(NaoEncontradoException ex) {
        return new ExceptionBuilder(
                Response.Status.NOT_FOUND.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
