package org.brito.desafiojersey;

import org.brito.desafiojersey.config.Configurations;
import org.brito.desafiojersey.config.DatabaseMigration;
import org.brito.desafiojersey.security.JwtAuthenticationFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class DesafioJerseysBackendApplication {

    public static final String HOST = Configurations.getHost();
    public static final String BASE_URI = HOST + "/api/";

    public static void main(String[] args) {

        DatabaseMigration.iniciaBanco();
        final ResourceConfig rc = new ResourceConfig()
                .packages("org.brito.desafiojersey.controller")
                .register(JwtAuthenticationFilter.class);
        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        System.out.println("Api disponível em " + BASE_URI);
    }
}
