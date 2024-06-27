package org.brito.desafiojersey.security;

public interface AuthenticationService {

    String buscarToken(String login, String password);

}
