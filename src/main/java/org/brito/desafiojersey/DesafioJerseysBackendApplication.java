package org.brito.desafiojersey;

import org.brito.desafiojersey.config.Configurations;
import org.brito.desafiojersey.config.DatabaseMigration;
import org.brito.desafiojersey.config.ModelMapperProvider;
import org.brito.desafiojersey.config.ObjectMapper;
import org.brito.desafiojersey.dao.*;
import org.brito.desafiojersey.dao.implementation.*;
import org.brito.desafiojersey.exceptions.ClienteException;
import org.brito.desafiojersey.exceptions.ConteinerException;
import org.brito.desafiojersey.exceptions.MovimentacaoException;
import org.brito.desafiojersey.exceptions.mapper.CarregamentoExceptionMapper;
import org.brito.desafiojersey.exceptions.mapper.NaoEncontradoExceptionMapper;
import org.brito.desafiojersey.exceptions.mapper.UsuarioExceptionMapper;
import org.brito.desafiojersey.exceptions.mapper.ValidacaoExceptionMapper;
import org.brito.desafiojersey.security.JwtAuthenticationFilter;
import org.brito.desafiojersey.services.*;
import org.brito.desafiojersey.services.implementation.*;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.modelmapper.ModelMapper;

import java.net.URI;

public class DesafioJerseysBackendApplication {

    public static final String HOST = Configurations.getHost();
    public static final String BASE_URI = HOST + "/api/";

    public static void main(String[] args) {

        DatabaseMigration.iniciaBanco();
        final ResourceConfig rc = new ResourceConfig()
                .packages("org.brito.desafiojersey.controller")
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(ValidacaoServiceImpl.class).to(ValidacaoService.class);
                        bind(UsuarioDAOImpl.class).to(UsuarioDAO.class);
                        bind(UsuarioServiceImpl.class).to(UsuarioService.class);
                        bind(ModelMapper.class).to(ModelMapper.class);
                        bind(ClienteDAOImpl.class).to(ClienteDAO.class);
                        bind(ClienteServiceImpl.class).to(ClienteService.class);
                        bind(ConteinerDAOImpl.class).to(ConteinerDAO.class);
                        bind(ConteinerServiceImpl.class).to(ConteinerService.class);
                        bind(MovimentacaoDAOImpl.class).to(MovimentacaoDAO.class);
                        bind(MovimentacaoServiceImpl.class).to(MovimentacaoService.class);
                        bind(ConteineresMovimentacoesDAOImpl.class).to(ConteineresMovimentacoesDAO.class);

                    }
                })
                .register(JwtAuthenticationFilter.class)
                .register(ValidacaoExceptionMapper.class)
                .register(UsuarioExceptionMapper.class)
                .register(NaoEncontradoExceptionMapper.class)
                .register(CarregamentoExceptionMapper.class)
                .register(ModelMapperProvider.class)
                .register(ClienteException.class)
                .register(ConteinerException.class)
                .register(MovimentacaoException.class)
                .register(new ObjectMapper());

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        System.out.println("Api disponível em " + BASE_URI);
    }
}
