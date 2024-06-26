package org.brito.desafiojersey.services;

import org.brito.desafiojersey.security.AuthTokenDTO;
import org.brito.desafiojersey.security.UsuarioCredenciaisDTO;

public interface ValidacaoService {

    AuthTokenDTO validaLogin(UsuarioCredenciaisDTO credentials) throws Exception;
}
