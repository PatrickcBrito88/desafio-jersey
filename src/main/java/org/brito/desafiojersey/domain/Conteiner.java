package org.brito.desafiojersey.domain;

import org.brito.desafiojersey.enums.ECategoria;
import org.brito.desafiojersey.enums.EStatus;
import org.brito.desafiojersey.enums.ETipoConteiner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Conteiner {

    private long id;

    private String identificacao;

    private Cliente cliente;

    private ETipoConteiner tipo;

    private ECategoria categoria;

    private EStatus status;

    private List<Movimentacao> movimentacoes = new ArrayList<>();

    public Conteiner() {
    }


    public Conteiner(long id, String identificacao,
                     Cliente cliente, ETipoConteiner tipo,
                     ECategoria categoria, EStatus status) {
        this.id = id;
        this.identificacao = identificacao;
        this.cliente = cliente;
        this.tipo = tipo;
        this.categoria = categoria;
        this.status = status;
        this.movimentacoes = movimentacoes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setTipo(String tipo) {
        this.tipo = Objects.isNull(tipo) ? null : ETipoConteiner.valueOf(tipo);
    }

    public ECategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = Objects.isNull(categoria) ? null : ECategoria.valueOf(categoria);
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Objects.isNull(status) ? null : EStatus.valueOf(status);
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}
