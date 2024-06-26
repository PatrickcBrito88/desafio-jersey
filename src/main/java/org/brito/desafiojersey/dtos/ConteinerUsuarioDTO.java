package org.brito.desafiojersey.dtos;

import java.util.List;

public class ConteinerUsuarioDTO {

    private UsuarioDTO usuario;
    private List<ConteinerDTO> conteineres;


    public ConteinerUsuarioDTO(UsuarioDTO usuario, List<ConteinerDTO> conteineres) {
        this.conteineres = conteineres;
        this.usuario = usuario;
    }

    public List<ConteinerDTO> getConteineres() {
        return conteineres;
    }

    public void setConteineres(List<ConteinerDTO> conteineres) {
        this.conteineres = conteineres;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
