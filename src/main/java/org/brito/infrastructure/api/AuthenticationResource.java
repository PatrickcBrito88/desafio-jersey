package org.brito.infrastructure.api;


import jakarta.ws.rs.*;
import org.brito.annotations.NaoAutenticado;
import org.brito.security.AuthTokenDTO;
import org.brito.security.UsuarioCredenciaisDTO;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.security.AuthenticationService;
import org.brito.utils.MessageUtils;

import java.util.Objects;

@Path("/auth")
public class AuthenticationResource {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @NaoAutenticado
    public Response login(UsuarioCredenciaisDTO credentials) {
        try {
            String token = AuthenticationService.buscarToken(credentials.getLogin(), credentials.getPassword());
            if (Objects.isNull(token)) {
                return Response.status(
                        Response.Status.UNAUTHORIZED).entity(
                                MessageUtils.buscaValidacao("credenciais.invalidas")).build();
            }
            return Response.ok(new AuthTokenDTO(token)).build();
        } catch (Exception e) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR).entity(
                            MessageUtils.buscaValidacao("error.servidor")).build();
        }
    }

    @GET
    @Path("/teste")
    public Response teste() {
        return Response.ok("Teste").build();
    }

}


