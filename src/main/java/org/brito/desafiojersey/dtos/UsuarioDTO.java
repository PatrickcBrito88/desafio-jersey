package org.brito.desafiojersey.dtos;

import org.brito.desafiojersey.enums.ERole;
import org.brito.desafiojersey.security.UsuarioCredenciaisDTO;

public class UsuarioDTO extends UsuarioCredenciaisDTO {

    private long id;
    private ERole role;

    public UsuarioDTO() {

    }


    public UsuarioDTO(String login, String password, ERole role) {
        super(login, password);
        this.role = role;
    }

    public UsuarioDTO(String id, String login, String password, ERole role) {
        super(login, password);
        this.role = role;
    }

    public void setRole(ERole role) {
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
}
