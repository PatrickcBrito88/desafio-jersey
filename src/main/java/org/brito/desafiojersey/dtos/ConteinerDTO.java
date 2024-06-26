package org.brito.desafiojersey.dtos;

import org.brito.desafiojersey.enums.ECategoria;
import org.brito.desafiojersey.enums.EStatus;
import org.brito.desafiojersey.enums.ETipoConteiner;

import java.util.ArrayList;
import java.util.List;

public class ConteinerDTO {

    private String identificacao;

    private Integer clienteId;

    private ETipoConteiner tipo;

    private ECategoria categoria;

    private EStatus status;

    private List<MovimentacaoDTO> movimentacoes = new ArrayList<>();

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public ETipoConteiner getTipo() {
        return tipo;
    }

    public void setTipo(ETipoConteiner tipo) {
        this.tipo = tipo;
    }

    public ECategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ECategoria categoria) {
        this.categoria = categoria;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public List<MovimentacaoDTO> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacaoDTO> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}
