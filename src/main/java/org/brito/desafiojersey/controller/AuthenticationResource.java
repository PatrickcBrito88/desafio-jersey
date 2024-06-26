package org.brito.desafiojersey.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.annotations.NaoAutenticado;
import org.brito.desafiojersey.security.UsuarioCredenciaisDTO;
import org.brito.desafiojersey.services.ValidacaoService;

@Path("/auth")
public class AuthenticationResource implements DefaultController {


    private final ValidacaoService validacaoService;

    @Inject
    public AuthenticationResource(ValidacaoService validacaoService) {
        this.validacaoService = validacaoService;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @NaoAutenticado
    public Response login(UsuarioCredenciaisDTO credentials) throws Exception {
        return retornarSucesso(validacaoService.validaLogin(credentials));
    }


    @GET
    @Path("/teste")
    @Produces(MediaType.APPLICATION_JSON)
    public Response teste() {
        return retornarSucesso("Teste ok");
    }

}


