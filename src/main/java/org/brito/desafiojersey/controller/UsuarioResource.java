package org.brito.desafiojersey.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.dtos.UsuarioDTO;
import org.brito.desafiojersey.services.UsuarioService;

@Path("/usuario")
public class UsuarioResource implements DefaultController {

    private final UsuarioService usuarioService;

    @Inject
    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(UsuarioDTO usuarioDTO) throws Exception {
        return retornarSucesso(usuarioService.salvarUsuario(usuarioDTO));
    }

    @GET
    @Path("/buscar-por-id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(usuarioService.buscarUsuarioPorId(id));
    }

    @PUT
    @Path("/editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(UsuarioDTO usuarioDTO, @PathParam("id") Integer id) throws Exception {
        return retornarSucesso(usuarioService.atualizarUsuario(usuarioDTO, id));
    }

    @DELETE
    @Path("/excluir/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(usuarioService.deletarUsuario(id));
    }

    @GET
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                           @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina) throws Exception {
        return retornarSucesso(usuarioService.listarUsuariosPaginado(paginaAtual, tamanhoPagina));
    }


}


