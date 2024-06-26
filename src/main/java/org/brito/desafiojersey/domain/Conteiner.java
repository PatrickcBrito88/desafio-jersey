package org.brito.desafiojersey.domain;

import org.brito.desafiojersey.enums.ECategoria;
import org.brito.desafiojersey.enums.EStatus;
import org.brito.desafiojersey.enums.ETipoConteiner;

import java.util.ArrayList;
import java.util.List;

public class Conteiner {

    private Long id;

    private String identificacao;

    private Cliente cliente;

    private ETipoConteiner tipo;

    private ECategoria categoria;

    private EStatus status;

    private List<Movimentacao> movimentacoes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}
