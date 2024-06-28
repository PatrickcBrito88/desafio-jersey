package org.brito.desafiojersey.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.dtos.MovimentacaoDTO;
import org.brito.desafiojersey.services.MovimentacaoService;

/**
 * Recurso de movimentação para a aplicação.
 * Fornece endpoints para operações CRUD em movimentações.
 *
 * Esta classe é responsável por expor os endpoints de API relacionados às movimentações,
 * permitindo que as movimentações sejam criadas, buscadas, fechadas e listadas.
 *
 * Utiliza o serviço MovimentacaoService para realizar as operações de negócio.
 *
 * Os métodos desta classe consomem e produzem JSON e podem lançar exceções
 * que são tratadas globalmente pela aplicação.
 *
 * @see MovimentacaoService
 * @see MovimentacaoDTO
 *
 * @autor Patrick Brito
 */
@Path("/movimentacao")
public class MovimentacaoResource implements DefaultController {

    private final MovimentacaoService movimentacaoService;

    /**
     * Construtor que injeta o serviço de movimentação.
     *
     * @param movimentacaoService o serviço de movimentação a ser injetado.
     */
    @Inject
    public MovimentacaoResource(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    /**
     * Endpoint para criar uma nova movimentação.
     * Consome um DTO de movimentação e produz uma resposta JSON.
     *
     * @param movimentacaoDTO os dados da movimentação a ser criada.
     * @return uma resposta indicando o sucesso ou falha da criação.
     * @throws Exception se ocorrer algum erro durante a criação.
     */
    @POST
    @Path("/criar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criar(@Valid MovimentacaoDTO movimentacaoDTO) throws Exception {
        return retornarSucesso(movimentacaoService.criarMovimentacao(movimentacaoDTO));
    }

    /**
     * Endpoint para buscar uma movimentação por ID.
     * Produz uma resposta JSON com os dados da movimentação.
     *
     * @param id o ID da movimentação a ser buscada.
     * @return uma resposta com os dados da movimentação encontrada ou uma mensagem de erro.
     * @throws Exception se ocorrer algum erro durante a busca.
     */
    @GET
    @Path("/buscar-por-id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(movimentacaoService.buscarMovimentacaoPorId(id));
    }

    /**
     * Endpoint para fechar uma movimentação existente.
     * Produz uma resposta JSON indicando o sucesso ou falha da operação.
     *
     * @param id o ID da movimentação a ser fechada.
     * @return uma resposta indicando o sucesso ou falha do fechamento.
     * @throws Exception se ocorrer algum erro durante o fechamento.
     */
    @PUT
    @Path("/fechar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fechar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(movimentacaoService.fecharMovimentacao(id));
    }

    /**
     * Endpoint para listar movimentações com paginação.
     * Produz uma resposta JSON com a lista paginada de movimentações.
     *
     * @param paginaAtual o número da página atual.
     * @param tamanhoPagina o tamanho da página.
     * @return uma resposta com a lista paginada de movimentações.
     * @throws Exception se ocorrer algum erro durante a listagem.
     */
    @GET
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                           @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina) throws Exception {
        return retornarSucesso(movimentacaoService.listaMovimentacoes(paginaAtual, tamanhoPagina));
    }

    /**
     * Endpoint para listar movimentações por contêiner com paginação.
     * Produz uma resposta JSON com a lista paginada de movimentações de um contêiner específico.
     *
     * @param paginaAtual o número da página atual.
     * @param tamanhoPagina o tamanho da página.
     * @param idConteiner o ID do contêiner cujas movimentações serão listadas.
     * @return uma resposta com a lista paginada de movimentações do contêiner.
     * @throws Exception se ocorrer algum erro durante a listagem.
     */
    @GET
    @Path("/listar-por-conteiner/{idConteiner}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorConteiner(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                                       @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina,
                                       @PathParam("idConteiner") Integer idConteiner) throws Exception {
        return retornarSucesso(movimentacaoService.listaMovimentacoesPorContainer(paginaAtual, tamanhoPagina, idConteiner));
    }

    /**
     * Endpoint para listar movimentações por cliente com paginação.
     * Produz uma resposta JSON com a lista paginada de movimentações de um cliente específico.
     *
     * @param paginaAtual o número da página atual.
     * @param tamanhoPagina o tamanho da página.
     * @param idCliente o ID do cliente cujas movimentações serão listadas.
     * @return uma resposta com a lista paginada de movimentações do cliente.
     * @throws Exception se ocorrer algum erro durante a listagem.
     */
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
