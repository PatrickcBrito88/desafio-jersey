package org.brito.desafiojersey.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.annotations.NaoAutenticado;
import org.brito.desafiojersey.security.dtos.UsuarioCredenciaisDTO;
import org.brito.desafiojersey.services.ValidacaoService;

/**
 * Recurso de autenticação para a aplicação.
 * Fornece endpoints para validação de login
 *
 * Esta classe é responsável por expor os endpoints de API relacionados à autenticação,
 * permitindo que os usuários façam login e testem a conectividade do serviço.
 *
 * Utiliza o serviço ValidacaoService para realizar a validação das credenciais de login.
 *
 * Os métodos desta classe consomem e produzem JSON e podem lançar exceções
 * que são tratadas globalmente pela aplicação.
 *
 * @see ValidacaoService
 * @see UsuarioCredenciaisDTO
 * @see DefaultController
 * @see Response
 *
 * @autor Patrick Brito
 */
@Path("/auth")
public class AuthenticationResource implements DefaultController {

    private final ValidacaoService validacaoService;

    /**
     * Construtor que injeta o serviço de validação.
     *
     * @param validacaoService o serviço de validação a ser injetado.
     */
    @Inject
    public AuthenticationResource(ValidacaoService validacaoService) {
        this.validacaoService = validacaoService;
    }

    /**
     * Endpoint para login.
     * Consome um DTO de credenciais de usuário e produz uma resposta JSON.
     * Este endpoint não requer autenticação.
     *
     * @param credentials as credenciais do usuário.
     * @return uma resposta indicando o sucesso ou falha da autenticação.
     * @throws Exception se ocorrer algum erro durante a autenticação.
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @NaoAutenticado
    public Response login(UsuarioCredenciaisDTO credentials) throws Exception {
        return retornarSucesso(validacaoService.validaLogin(credentials));
    }

}
