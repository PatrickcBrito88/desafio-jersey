package org.brito.desafiojersey;

import org.brito.desafiojersey.config.DatabaseMigrationConfig;
import org.brito.desafiojersey.config.ObjectMapperConfig;
import org.brito.desafiojersey.config.VariaveisAmbienteConfig;
import org.brito.desafiojersey.provider.ExceptionsMapeadasProvider;
import org.brito.desafiojersey.provider.ServicosMapeadosProvider;
import org.brito.desafiojersey.security.JwtAuthenticationFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class DesafioJerseysBackendApplication {

    public static void main(String[] args) {
        DatabaseMigrationConfig.iniciaBanco();
        String baseUri = VariaveisAmbienteConfig.getHost() + "/api/";
        ResourceConfig resourceConfig = buildResourceConfig();
        startServer(baseUri, resourceConfig);

    }

    private static ResourceConfig buildResourceConfig() {
        return new ResourceConfig()
                .packages("org.brito.desafiojersey.controller", "org.brito.desafiojersey.exceptions.mapper")
                .register(new ServicosMapeadosProvider())
                .register(ExceptionsMapeadasProvider.class)
                .register(new ObjectMapperConfig())
                .register(JwtAuthenticationFilter.class);
    }

    private static void startServer(String uri, ResourceConfig resourceConfig) {
        URI serverUri = URI.create(uri);
        GrizzlyHttpServerFactory.createHttpServer(serverUri, resourceConfig);
        System.out.println("API disponível em " + uri);
    }

}
