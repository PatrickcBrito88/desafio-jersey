package org.brito.desafiojersey.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class DefaultResponse<T> {
    private Integer status;
    private T dados;

    public DefaultResponse() {
    }

    public DefaultResponse(final Integer status, final T dados) {
        this.status = status;
        this.dados = dados;
    }

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
