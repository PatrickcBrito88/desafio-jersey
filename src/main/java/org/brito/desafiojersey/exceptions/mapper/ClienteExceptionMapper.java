package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.brito.desafiojersey.exceptions.ClienteException;

@Provider
public class ClienteExceptionMapper implements ExceptionMapper<ClienteException> {

    @Override
    public Response toResponse(ClienteException ex) {
        return new ExceptionBuilder(
                Response.Status.BAD_REQUEST.getStatusCode(),
                ex.getMessage()
        ).build();
    }
}
