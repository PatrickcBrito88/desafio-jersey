package org.brito.desafiojersey.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.brito.desafiojersey.annotations.NaoAutenticado;
import org.brito.desafiojersey.utils.MessageUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Key;
import java.util.Objects;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();

        if (Objects.nonNull(method)
                && (method.isAnnotationPresent(NaoAutenticado.class)
                || resourceInfo.getResourceClass().isAnnotationPresent(NaoAutenticado.class))
        ) {
            return;
        }

        String authorizationHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);

        if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).entity(
                            MessageUtils.buscaValidacao("auth.nao.autenticado")).build());
            return;
        }

        String token = authorizationHeader.substring(BEARER_PREFIX.length()).trim();
        try {
            Key key = AuthenticationService.getKey();
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);

        } catch (JwtException | IllegalArgumentException e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).entity(
                            MessageUtils.buscaValidacao("auth.token.invalido")).build());
        }
    }
}
