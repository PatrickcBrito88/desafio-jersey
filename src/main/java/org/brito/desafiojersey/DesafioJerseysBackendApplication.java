package org.brito.desafiojersey;

import org.brito.desafiojersey.config.Configurations;
import org.brito.desafiojersey.config.DatabaseMigration;
import org.brito.desafiojersey.config.ExceptionsMapeadas;
import org.brito.desafiojersey.config.ServicosMapeados;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class DesafioJerseysBackendApplication {

    public static void main(String[] args) {
        DatabaseMigration.iniciaBanco();
        String baseUri = Configurations.getHost() + "/api/";
        ResourceConfig resourceConfig = buildResourceConfig();
        startServer(baseUri, resourceConfig);
    }

    private static ResourceConfig buildResourceConfig() {
        return new ResourceConfig()
                .packages("org.brito.desafiojersey.controller", "org.brito.desafiojersey.exceptions.mapper")
                .register(new ServicosMapeados())
                .register(ExceptionsMapeadas.class);
    }

    private static void startServer(String uri, ResourceConfig resourceConfig) {
        URI serverUri = URI.create(uri);
        GrizzlyHttpServerFactory.createHttpServer(serverUri, resourceConfig);
        System.out.println("API disponível em " + uri);
    }

}
