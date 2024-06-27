package org.brito.desafiojersey.controller;


import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.dtos.MovimentacaoDTO;
import org.brito.desafiojersey.services.MovimentacaoService;

@Path("/movimentacao")
public class MovimentacaoResource implements DefaultController {

    private final MovimentacaoService movimentacaoService;

    @Inject
    public MovimentacaoResource(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @POST
    @Path("/criar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criar(@Valid MovimentacaoDTO movimentacaoDTO) throws Exception {
        return retornarSucesso(movimentacaoService.criarMovimentacao(movimentacaoDTO));
    }

    @GET
    @Path("/buscar-por-id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(movimentacaoService.buscarMovimentacaoPorId(id));
    }

    @PUT
    @Path("/fechar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fechar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(movimentacaoService.fecharMovimentacao(id));
    }


    @GET
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                           @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina) throws Exception {
        return retornarSucesso(movimentacaoService.listaMovimentacoes(paginaAtual, tamanhoPagina));
    }

    @GET
    @Path("/listar-por-conteiner/{idConteiner}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorConteiner(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                                       @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina,
                                       @PathParam("idConteiner") Integer idConteiner) throws Exception {
        return retornarSucesso(movimentacaoService.listaMovimentacoesPorContainer(paginaAtual, tamanhoPagina, idConteiner));
    }

    @GET
    @Path("/listar-por-cliente/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorCliente(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                                       @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina,
                                       @PathParam("idCliente") Integer idCliente) throws Exception {
        return retornarSucesso(movimentacaoService.listaMovimentacoesPorCliente(paginaAtual, tamanhoPagina, idCliente));
    }


}


