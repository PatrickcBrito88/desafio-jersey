package org.brito.infrastructure.api;


import jakarta.ws.rs.*;
import org.brito.authentication.AuthToken;
import org.brito.authentication.UsuarioCredenciais;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.services.AuthenticationService;
import org.brito.utils.MessageUtils;

import java.util.Objects;

@Path("/auth")
public class AuthenticationResource {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UsuarioCredenciais credentials) {
        try {
            String token = AuthenticationService.authenticate(credentials.getLogin(), credentials.getPassword());
            if (Objects.isNull(token)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity(MessageUtils.buscaValidacao("credenciais.invalidas")).build();
            }
            return Response.ok(new AuthToken(token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
        }
    }

    @GET
    @Path("/hello")
    public Response hello() {
        return Response.ok("Hello World!").build();
    }

}


