package org.brito.desafiojersey.controller;


import jakarta.ws.rs.*;
import org.brito.desafiojersey.annotations.NaoAutenticado;
import org.brito.desafiojersey.security.AuthTokenDTO;
import org.brito.desafiojersey.security.AuthenticationService;
import org.brito.desafiojersey.security.UsuarioCredenciaisDTO;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationResource implements DefaultController {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @NaoAutenticado
    public Response login(UsuarioCredenciaisDTO credentials) {
        String token = AuthenticationService.buscarToken(credentials.getLogin(), credentials.getPassword());
        return retornarSucesso(new AuthTokenDTO(token));

    }

    @GET
    @Path("/teste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response teste() {
        return retornarSucesso("Teste ok");
    }

}


