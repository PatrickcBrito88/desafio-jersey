package org.brito.desafiojersey.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.dtos.ConteinerDTO;
import org.brito.desafiojersey.services.ConteinerService;

@Path("/conteiner")
public class ConteinerResource implements DefaultController {

    private final ConteinerService conteinerService;

    @Inject
    public ConteinerResource(ConteinerService conteinerService) {
        this.conteinerService = conteinerService;
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(ConteinerDTO conteinerDTO) throws Exception {
        return retornarSucesso(conteinerService.salvarConteiner(conteinerDTO));
    }

    @GET
    @Path("/buscar-por-id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(conteinerService.buscarConteinerPorId(id));
    }

    @PUT
    @Path("/editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(ConteinerDTO conteinerDTO, @PathParam("id") Integer id) throws Exception {
        return retornarSucesso(conteinerService.atualizarConteiner(conteinerDTO, id));
    }

    @DELETE
    @Path("/excluir/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(conteinerService.deletarConteiner(id));
    }

    @GET
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                           @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina) throws Exception {
        return retornarSucesso(conteinerService.listarConteinersPaginados(paginaAtual, tamanhoPagina));
    }


}


