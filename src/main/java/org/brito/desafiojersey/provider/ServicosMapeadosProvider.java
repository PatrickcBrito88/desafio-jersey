package org.brito.desafiojersey.provider;

import org.brito.desafiojersey.dao.*;
import org.brito.desafiojersey.dao.implementation.*;
import org.brito.desafiojersey.security.AuthenticationService;
import org.brito.desafiojersey.security.AuthenticationServiceImpl;
import org.brito.desafiojersey.services.*;
import org.brito.desafiojersey.services.implementation.*;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.modelmapper.ModelMapper;

public class ServicosMapeadosProvider extends AbstractBinder{

    @Override
    protected void configure() {

        bind(ValidacaoServiceImpl.class).to(ValidacaoService.class);
        bind(UsuarioServiceImpl.class).to(UsuarioService.class);
        bind(ClienteServiceImpl.class).to(ClienteService.class);
        bind(ConteinerServiceImpl.class).to(ConteinerService.class);
        bind(MovimentacaoServiceImpl.class).to(MovimentacaoService.class);
        bind(AuthenticationServiceImpl.class).to(AuthenticationService.class);
        bind(ModelMapper.class).to(ModelMapper.class);

        bind(UsuarioDAOImpl.class).to(UsuarioDAO.class);
        bind(ClienteDAOImpl.class).to(ClienteDAO.class);
        bind(ConteinerDAOImpl.class).to(ConteinerDAO.class);
        bind(MovimentacaoDAOImpl.class).to(MovimentacaoDAO.class);
        bind(ConteinerMovimentacaoDAOImpl.class).to(ConteinerMovimentacaoDAO.class);
    }

}
