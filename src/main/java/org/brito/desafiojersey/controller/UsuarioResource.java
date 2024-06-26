package org.brito.desafiojersey.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.dao.UsuarioDAO;
import org.brito.desafiojersey.dtos.UsuarioDTO;

@Path("/usuario")
public class UsuarioResource implements DefaultController {

    private final UsuarioDAO usuarioDAO;

    @Inject
    public UsuarioResource(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(UsuarioDTO usuarioDTO) throws Exception {
        return retornarSucesso(usuarioDAO.salvarUsuario(usuarioDTO));
    }

    @GET
    @Path("/buscar-por-id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(usuarioDAO.buscarUsuarioPorId(id));
    }

    @PUT
    @Path("/editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(UsuarioDTO usuarioDTO, @PathParam("id") Integer id) throws Exception {
        return retornarSucesso(usuarioDAO.atualizarUsuario(usuarioDTO, id));
    }

    @DELETE
    @Path("/excluir/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(usuarioDAO.deletarUsuario(id));
    }

    @GET
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() throws Exception {
        return retornarSucesso(usuarioDAO.listarUsuarios());
    }




}


