package org.brito.desafiojersey.dtos;

import java.util.List;

public class ConteinerUsuarioDTO {

    private ClienteDTO cliente;
    private List<ConteinerDTO> conteineres;


    public ConteinerUsuarioDTO(ClienteDTO cliente, List<ConteinerDTO> conteineres) {
        this.conteineres = conteineres;
        this.cliente = cliente;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ConteinerDTO> getConteineres() {
        return conteineres;
    }

    public void setConteineres(List<ConteinerDTO> conteineres) {
        this.conteineres = conteineres;
    }


}
