package org.brito.desafiojersey.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ObjectMapper implements ContextResolver<com.fasterxml.jackson.databind.ObjectMapper> {

    private final com.fasterxml.jackson.databind.ObjectMapper mapper;

    public ObjectMapper() {
        mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public com.fasterxml.jackson.databind.ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
}
