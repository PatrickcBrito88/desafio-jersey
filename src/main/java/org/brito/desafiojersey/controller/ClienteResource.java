package org.brito.desafiojersey.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.dtos.ClienteDTO;
import org.brito.desafiojersey.services.ClienteService;

@Path("/cliente")
public class ClienteResource implements DefaultController {

    private final ClienteService clienteService;

    @Inject
    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(ClienteDTO clienteDTO) throws Exception {
        return retornarSucesso(clienteService.salvarCliente(clienteDTO));
    }

    @GET
    @Path("/buscar-por-id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(clienteService.buscarClientePorId(id));
    }

    @PUT
    @Path("/editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(ClienteDTO clienteDTO, @PathParam("id") Integer id) throws Exception {
        return retornarSucesso(clienteService.atualizarCliente(clienteDTO, id));
    }

    @DELETE
    @Path("/excluir/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(clienteService.deletarCliente(id));
    }

    @GET
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                           @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina) throws Exception {
        return retornarSucesso(clienteService.listarClientesPaginados(paginaAtual, tamanhoPagina));
    }


}


