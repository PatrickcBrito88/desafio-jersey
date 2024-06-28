package org.brito.desafiojersey.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.dtos.UsuarioDTO;
import org.brito.desafiojersey.services.UsuarioService;

/**
 * Recurso de usuário para a aplicação.
 * Fornece endpoints para operações CRUD em usuários.
 *
 * Esta classe é responsável por expor os endpoints de API relacionados aos usuários,
 * permitindo que os usuários sejam cadastrados, buscados, editados, excluídos e listados.
 *
 * Utiliza o serviço UsuarioService para realizar as operações de negócio.
 *
 * Os métodos desta classe consomem e produzem JSON e podem lançar exceções
 * que são tratadas globalmente pela aplicação.
 *
 * @see UsuarioService
 * @see UsuarioDTO
 *
 * @autor Patrick Brito
 */
@Path("/usuario")
public class UsuarioResource implements DefaultController {

    private final UsuarioService usuarioService;

    /**
     * Construtor que injeta o serviço de usuário.
     *
     * @param usuarioService o serviço de usuário a ser injetado.
     */
    @Inject
    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para cadastrar um novo usuário.
     * Consome um DTO de usuário e produz uma resposta JSON.
     *
     * @param usuarioDTO os dados do usuário a ser cadastrado.
     * @return uma resposta indicando o sucesso ou falha do cadastro.
     * @throws Exception se ocorrer algum erro durante o cadastro.
     */
    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(@Valid UsuarioDTO usuarioDTO) throws Exception {
        return retornarSucesso(usuarioService.salvarUsuario(usuarioDTO));
    }

    /**
     * Endpoint para buscar um usuário por ID.
     * Produz uma resposta JSON com os dados do usuário.
     *
     * @param id o ID do usuário a ser buscado.
     * @return uma resposta com os dados do usuário encontrado ou uma mensagem de erro.
     * @throws Exception se ocorrer algum erro durante a busca.
     */
    @GET
    @Path("/buscar-por-id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(usuarioService.buscarUsuarioPorId(id));
    }

    /**
     * Endpoint para editar um usuário existente.
     * Consome um DTO de usuário e o ID do usuário a ser editado, e produz uma resposta JSON.
     *
     * @param usuarioDTO os novos dados do usuário.
     * @param id o ID do usuário a ser editado.
     * @return uma resposta indicando o sucesso ou falha da edição.
     * @throws Exception se ocorrer algum erro durante a edição.
     */
    @PUT
    @Path("/editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(@Valid UsuarioDTO usuarioDTO, @PathParam("id") Integer id) throws Exception {
        return retornarSucesso(usuarioService.atualizarUsuario(usuarioDTO, id));
    }

    /**
     * Endpoint para excluir um usuário por ID.
     * Produz uma resposta JSON indicando o sucesso ou falha da exclusão.
     *
     * @param id o ID do usuário a ser excluído.
     * @return uma resposta indicando o sucesso ou falha da exclusão.
     * @throws Exception se ocorrer algum erro durante a exclusão.
     */
    @DELETE
    @Path("/excluir/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") Integer id) throws Exception {
        return retornarSucesso(usuarioService.deletarUsuario(id));
    }

    /**
     * Endpoint para listar usuários com paginação.
     * Produz uma resposta JSON com a lista paginada de usuários.
     *
     * @param paginaAtual o número da página atual.
     * @param tamanhoPagina o tamanho da página.
     * @return uma resposta com a lista paginada de usuários.
     * @throws Exception se ocorrer algum erro durante a listagem.
     */
    @GET
    @Path("/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@QueryParam("paginaAtual") @DefaultValue("0") int paginaAtual,
                           @QueryParam("tamanhoPagina") @DefaultValue("10") int tamanhoPagina) throws Exception {
        return retornarSucesso(usuarioService.listarUsuariosPaginado(paginaAtual, tamanhoPagina));
    }
}
