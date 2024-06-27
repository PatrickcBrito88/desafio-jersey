package org.brito.desafiojersey.domain;

public class ConteinerMovimentacao {

    private long conteinerId;

    private long movimentacaoId;

    public ConteinerMovimentacao(long conteinerId, long movimentacaoId) {
        this.conteinerId = conteinerId;
        this.movimentacaoId = movimentacaoId;
    }

    public long getConteinerId() {
        return conteinerId;
    }

    public void setConteinerId(long conteinerId) {
        this.conteinerId = conteinerId;
    }

    public long getMovimentacaoId() {
        return movimentacaoId;
    }

    public void setMovimentacaoId(long movimentacaoId) {
        this.movimentacaoId = movimentacaoId;
    }
}
