package org.brito.desafiojersey.config;

import org.brito.desafiojersey.exceptions.mapper.*;
import org.glassfish.jersey.server.ResourceConfig;

public class ExceptionsMapeadas extends ResourceConfig {
    public ExceptionsMapeadas() {
        register(ValidacaoExceptionMapper.class);
        register(UsuarioExceptionMapper.class);
        register(NaoEncontradoExceptionMapper.class);
        register(CarregamentoExceptionMapper.class);
        register(ClienteExceptionMapper.class);
        register(ConteinerExceptionMapper.class);
        register(MovimentacaoExceptionMapper.class);
        register(DatabaseExceptionMapper.class);
        register(ConflitoExceptionMapper.class);
    }

}
