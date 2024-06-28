package org.brito.desafiojersey.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.dtos.ClienteDTO;
import org.brito.desafiojersey.services.ClienteService;

/**
 * Recurso de cliente para a aplicação.
 * Fornece endpoints para operações CRUD em clientes.
 *
 * Esta classe é responsável por expor os endpoints de API relacionados aos clientes,
 * permitindo que os clientes sejam cadastrados, buscados, editados, excluídos e listados.
 *
 * Utiliza o serviço ClienteService para realizar as operações de negócio.
 *
 * Os métodos desta classe consomem e produzem JSON e podem lançar exceções
 * que são tratadas globalmente pela aplicação.
 *
 * @see ClienteService
 * @see ClienteDTO
 *
 * @autor Patrick Brito
 */
@Path("/cliente")
public class ClienteResource implements DefaultController {

    private final ClienteService clienteService;

    /**
     * Construtor que injeta o serviço de cliente.
     *
     * @param clienteService o serviço de cliente a ser injetado.
     */
    @Inject
    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Endpoint para cadastrar um novo cliente.
     * Consome um DTO de cliente e produz uma resposta JSON.
     *
     * @param clienteDTO os dados do cliente a ser cadastrado.
     * @return uma resposta indicando o sucesso ou falha do cadastro.
     * @throws Exception se ocorrer algum erro durante o cadastro.
     */
    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(@Valid ClienteDTO clienteDTO) throws Exception {
        return retornarSucesso(clienteService.salvarCliente(clienteDTO));
    }

    /**
     * Endpoint para buscar um cliente por ID.
     * Produz uma resposta JSON com os dados do cliente.
     *
     * @param id o ID do cliente a ser buscado.
     * @return uma resposta com os dados do cliente encontrado ou uma mensagem de erro.
     * @throws Exception se ocorrer algum erro durante a busca.
     */
    @GET
    @Path("/buscar-por-id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(clienteService.buscarClientePorId(id));
    }

    /**
     * Endpoint para editar um cliente existente.
     * Consome um DTO de cliente e o ID do cliente a ser editado, e produz uma resposta JSON.
     *
     * @param clienteDTO os novos dados do cliente.
     * @param id o ID do cliente a ser editado.
     * @return uma resposta indicando o sucesso ou falha da edição.
     * @throws Exception se ocorrer algum erro durante a edição.
     */
    @PUT
    @Path("/editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(@Valid ClienteDTO clienteDTO, @PathParam("id") Integer id) throws Exception {
        return retornarSucesso(clienteService.atualizarCliente(clienteDTO, id));
    }

    /**
     * Endpoint para excluir um cliente por ID.
     * Produz uma resposta JSON indicando o sucesso ou falha da exclusão.
     *
     * @param id o ID do cliente a ser excluído.
     * @return uma resposta indicando o sucesso ou falha da exclusão.
     * @throws Exception se ocorrer algum erro durante a exclusão.
     */
    @DELETE
    @Path("/excluir/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(clienteService.deletarCliente(id));
    }

    /**
     * Endpoint para listar clientes com paginação.
     * Produz uma resposta JSON com a lista paginada de clientes.
     *
     * @param paginaAtual o número da página atual.
     * @param tamanhoPagina o tamanho da página.
     * @return uma resposta com a lista paginada de clientes.
     * @throws Exception se ocorrer algum erro durante a listagem.
     */
    @GET
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                           @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina) throws Exception {
        return retornarSucesso(clienteService.listarClientesPaginados(paginaAtual, tamanhoPagina));
    }
}
