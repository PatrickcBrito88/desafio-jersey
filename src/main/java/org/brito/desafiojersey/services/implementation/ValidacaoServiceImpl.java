package org.brito.desafiojersey.services.implementation;

import jakarta.inject.Singleton;
import org.brito.desafiojersey.exceptions.ValidacaoException;
import org.brito.desafiojersey.security.AuthTokenDTO;
import org.brito.desafiojersey.security.AuthenticationService;
import org.brito.desafiojersey.security.UsuarioCredenciaisDTO;
import org.brito.desafiojersey.services.ValidacaoService;
import org.brito.desafiojersey.utils.MessageUtils;

import java.util.Objects;

@Singleton
public class ValidacaoServiceImpl implements ValidacaoService {


    @Override
    public AuthTokenDTO validaLogin(UsuarioCredenciaisDTO credentials) throws Exception {
        try {
            String token = AuthenticationService.buscarToken(credentials.getLogin(), credentials.getPassword());
            if (Objects.isNull(token)) {
                throw new ValidacaoException(MessageUtils.buscaValidacao("auth.credenciais.invalidas"));
            }
            return new AuthTokenDTO(token);
        } catch (Exception e) {
            throw new ValidacaoException(e.getMessage());
        }
    }
}
