package org.brito.desafiojersey.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlRootElement; // Para suporte a XML, se necessário

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement // Assegura que a classe pode ser convertida para XML, se necessário
public class DefaultResponse<T> {
    private Integer status;
    private T dados;

    public DefaultResponse() {
    }

    public DefaultResponse(final Integer status, final T dados) {
        this.status = status;
        this.dados = dados;
    }

    // Getters e setters são importantes para que o JSON-B e o JAXB possam funcionar corretamente
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }
}
