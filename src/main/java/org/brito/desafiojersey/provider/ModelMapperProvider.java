package org.brito.desafiojersey.provider;

import jakarta.inject.Singleton;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.modelmapper.ModelMapper;

public class ModelMapperProvider extends AbstractBinder {

    @Override
    protected void configure() {
        bind(ModelMapper.class)
                .to(ModelMapper.class)
                .in(Singleton.class);
    }

}
