package org.brito.desafiojersey.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.dtos.ConteinerDTO;
import org.brito.desafiojersey.services.ConteinerService;

/**
 * Recurso de contêiner para a aplicação.
 * Fornece endpoints para operações CRUD em contêineres.
 *
 * Esta classe é responsável por expor os endpoints de API relacionados aos contêineres,
 * permitindo que os contêineres sejam cadastrados, buscados, editados, excluídos e listados.
 *
 * Utiliza o serviço ConteinerService para realizar as operações de negócio.
 *
 * Os métodos desta classe consomem e produzem JSON e podem lançar exceções
 * que são tratadas globalmente pela aplicação.
 *
 * @see ConteinerService
 * @see ConteinerDTO
 *
 * @autor Patrick Brito
 */
@Path("/conteiner")
public class ConteinerResource implements DefaultController {

    private final ConteinerService conteinerService;

    /**
     * Construtor que injeta o serviço de contêiner.
     *
     * @param conteinerService o serviço de contêiner a ser injetado.
     */
    @Inject
    public ConteinerResource(ConteinerService conteinerService) {
        this.conteinerService = conteinerService;
    }

    /**
     * Endpoint para cadastrar um novo contêiner.
     * Consome um DTO de contêiner e produz uma resposta JSON.
     *
     * @param conteinerDTO os dados do contêiner a ser cadastrado.
     * @return uma resposta indicando o sucesso ou falha do cadastro.
     * @throws Exception se ocorrer algum erro durante o cadastro.
     */
    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(@Valid ConteinerDTO conteinerDTO) throws Exception {
        return retornarSucesso(conteinerService.salvarConteiner(conteinerDTO));
    }

    /**
     * Endpoint para buscar um contêiner por ID.
     * Produz uma resposta JSON com os dados do contêiner.
     *
     * @param id o ID do contêiner a ser buscado.
     * @return uma resposta com os dados do contêiner encontrado ou uma mensagem de erro.
     * @throws Exception se ocorrer algum erro durante a busca.
     */
    @GET
    @Path("/buscar-por-id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(conteinerService.buscarConteinerPorId(id));
    }

    /**
     * Endpoint para editar um contêiner existente.
     * Consome um DTO de contêiner e o ID do contêiner a ser editado, e produz uma resposta JSON.
     *
     * @param conteinerDTO os novos dados do contêiner.
     * @param id o ID do contêiner a ser editado.
     * @return uma resposta indicando o sucesso ou falha da edição.
     * @throws Exception se ocorrer algum erro durante a edição.
     */
    @PUT
    @Path("/editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(@Valid ConteinerDTO conteinerDTO, @PathParam("id") Integer id) throws Exception {
        return retornarSucesso(conteinerService.atualizarConteiner(conteinerDTO, id));
    }

    /**
     * Endpoint para excluir um contêiner por ID.
     * Produz uma resposta JSON indicando o sucesso ou falha da exclusão.
     *
     * @param id o ID do contêiner a ser excluído.
     * @return uma resposta indicando o sucesso ou falha da exclusão.
     * @throws Exception se ocorrer algum erro durante a exclusão.
     */
    @DELETE
    @Path("/excluir/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(conteinerService.deletarConteiner(id));
    }

    /**
     * Endpoint para listar contêineres com paginação.
     * Produz uma resposta JSON com a lista paginada de contêineres.
     *
     * @param paginaAtual o número da página atual.
     * @param tamanhoPagina o tamanho da página.
     * @return uma resposta com a lista paginada de contêineres.
     * @throws Exception se ocorrer algum erro durante a listagem.
     */
    @GET
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                           @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina) throws Exception {
        return retornarSucesso(conteinerService.listarConteinersPaginados(paginaAtual, tamanhoPagina));
    }

    /**
     * Endpoint para listar contêineres por cliente.
     * Produz uma resposta JSON com a lista de contêineres de um cliente específico.
     *
     * @param idCliente o ID do cliente cujos contêineres serão listados.
     * @return uma resposta com a lista de contêineres do cliente.
     * @throws Exception se ocorrer algum erro durante a listagem.
     */
    @GET
    @Path("/listar-por-cliente/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorCliente(@PathParam("idCliente") Integer idCliente) throws Exception {
        return retornarSucesso(conteinerService.listaConteineresPorCliente(idCliente));
    }
}
