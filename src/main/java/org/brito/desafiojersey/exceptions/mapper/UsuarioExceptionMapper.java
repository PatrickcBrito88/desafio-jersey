package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.brito.desafiojersey.exceptions.UsuarioException;

public class UsuarioExceptionMapper implements ExceptionMapper<UsuarioException> {

    @Override
    public Response toResponse(UsuarioException ex) {
        return new ExceptionBuilder(
                Response.Status.BAD_REQUEST.getStatusCode(),
                "usuario",
                ex.getMessage()
        ).build();
    }
}
