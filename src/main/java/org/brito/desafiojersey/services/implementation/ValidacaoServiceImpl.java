package org.brito.desafiojersey.services.implementation;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.brito.desafiojersey.exceptions.AutenticacaoException;
import org.brito.desafiojersey.security.AuthenticationService;
import org.brito.desafiojersey.security.dtos.AuthTokenDTO;
import org.brito.desafiojersey.security.dtos.UsuarioCredenciaisDTO;
import org.brito.desafiojersey.services.ValidacaoService;
import org.brito.desafiojersey.utils.MessageUtils;

import java.util.Objects;

@Singleton
public class ValidacaoServiceImpl implements ValidacaoService {

    private final AuthenticationService authenticationService;

    @Inject
    public ValidacaoServiceImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public AuthTokenDTO validaLogin(UsuarioCredenciaisDTO credentials) throws Exception {
        try {
            String token = authenticationService.buscarToken(credentials.getLogin(), credentials.getPassword());
            if (Objects.isNull(token)) {
                throw new AutenticacaoException(MessageUtils.buscaValidacao("auth.credenciais.invalidas"));
            }
            return new AuthTokenDTO(token);
        } catch (Exception e) {
            throw new AutenticacaoException(e.getMessage());
        }
    }
}
