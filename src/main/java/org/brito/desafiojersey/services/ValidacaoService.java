package org.brito.desafiojersey.services;

import org.brito.desafiojersey.security.dtos.AuthTokenDTO;
import org.brito.desafiojersey.security.dtos.UsuarioCredenciaisDTO;

public interface ValidacaoService {

    AuthTokenDTO validaLogin(UsuarioCredenciaisDTO credentials) throws Exception;
}
