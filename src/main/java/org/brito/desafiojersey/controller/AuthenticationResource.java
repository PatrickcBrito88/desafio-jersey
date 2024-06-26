package org.brito.desafiojersey.controller;


import jakarta.ws.rs.*;
import org.brito.desafiojersey.annotations.NaoAutenticado;
import org.brito.desafiojersey.security.AuthTokenDTO;
import org.brito.desafiojersey.security.AuthenticationService;
import org.brito.desafiojersey.security.UsuarioCredenciaisDTO;
import org.brito.desafiojersey.utils.MessageUtils;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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


