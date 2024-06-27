package org.brito.desafiojersey.provider;

import org.brito.desafiojersey.config.ObjectMapperConfig;
import org.brito.desafiojersey.exceptions.mapper.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.validation.ValidationFeature;

public class ExceptionsMapeadasProvider extends ResourceConfig {
    public ExceptionsMapeadasProvider() {
        register(AutenticacaoExceptionMapper.class);
        register(UsuarioExceptionMapper.class);
        register(NaoEncontradoExceptionMapper.class);
        register(CarregamentoExceptionMapper.class);
        register(ClienteExceptionMapper.class);
        register(ConteinerExceptionMapper.class);
        register(MovimentacaoExceptionMapper.class);
        register(DatabaseExceptionMapper.class);
        register(ConflitoExceptionMapper.class);
        register(ValidationFeature.class);
        register(ValidationExceptionMapper.class);
        register(ObjectMapperConfig.class);
    }

}
