package org.brito.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioCredenciaisDTO {

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

    public UsuarioCredenciaisDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UsuarioCredenciaisDTO() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
