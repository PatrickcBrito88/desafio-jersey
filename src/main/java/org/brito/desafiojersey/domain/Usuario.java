package org.brito.desafiojersey.domain;

import org.brito.desafiojersey.enums.ERole;

public class Usuario {

    private long id;
    private ERole role;
    private String login;
    private String password;

    public Usuario() {
    }

    public Usuario(long id, String login, ERole role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
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
