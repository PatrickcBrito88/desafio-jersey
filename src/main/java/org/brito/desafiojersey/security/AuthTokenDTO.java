package org.brito.desafiojersey.security;

public class AuthTokenDTO {

    private String token;

    public AuthTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
