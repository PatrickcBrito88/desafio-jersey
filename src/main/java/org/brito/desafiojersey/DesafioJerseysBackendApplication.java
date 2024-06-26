package org.brito.desafiojersey;

import org.brito.desafiojersey.config.Configurations;
import org.brito.desafiojersey.config.DatabaseMigration;
import org.brito.desafiojersey.security.JwtAuthenticationFilter;
import org.brito.desafiojersey.services.ValidacaoService;
import org.brito.desafiojersey.services.implementation.ValidacaoServiceImpl;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.internal.inject.AbstractBinder;


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
                    }
                })
                .register(JwtAuthenticationFilter.class);
        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        System.out.println("Api disponível em " + BASE_URI);
    }
}
