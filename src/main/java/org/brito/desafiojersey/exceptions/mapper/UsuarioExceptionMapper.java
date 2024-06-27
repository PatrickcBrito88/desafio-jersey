package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.brito.desafiojersey.exceptions.UsuarioException;

@Provider
public class UsuarioExceptionMapper implements ExceptionMapper<UsuarioException> {

    @Override
    public Response toResponse(UsuarioException ex) {
        System.out.println("Passou por aqui");
        return new ExceptionBuilder(
                Response.Status.BAD_REQUEST.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
