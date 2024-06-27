package org.brito.desafiojersey.security.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.brito.desafiojersey.annotations.MinimoSeisCaracteres;

public class UsuarioCredenciaisDTO {

    @JsonProperty("login")
    @NotBlank(message = "{usuario.login.em.branco}")
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "{usuario.password.em.branco}")
    @MinimoSeisCaracteres
    private String password;

    public UsuarioCredenciaisDTO(String login) {
        this.login = login;
    }

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
