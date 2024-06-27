package org.brito.desafiojersey.services;

import org.brito.desafiojersey.security.dtos.AuthTokenDTO;
import org.brito.desafiojersey.security.dtos.UsuarioCredenciaisDTO;

/**
 * Interface de serviço para validação de credenciais de usuários.
 */
public interface ValidacaoService {

    /**
     * Valida as credenciais de login de um usuário e retorna um token de autenticação.
     *
     * @param credentials {@link UsuarioCredenciaisDTO} contendo login e senha do usuário.
     * @return {@link AuthTokenDTO} contendo o token de autenticação.
     * @throws Exception Lança uma exceção caso a autenticação falhe ou ocorra um erro durante o processo.
     */
    AuthTokenDTO validaLogin(UsuarioCredenciaisDTO credentials) throws Exception;
}
