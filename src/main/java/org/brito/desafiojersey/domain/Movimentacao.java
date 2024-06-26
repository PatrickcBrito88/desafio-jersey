package org.brito.desafiojersey.domain;

import org.brito.desafiojersey.enums.ETipoMovimentacao;

import java.time.OffsetDateTime;

public class Movimentacao {

    private Long id;

    private ETipoMovimentacao tipo;

    private OffsetDateTime horaInicio;

    private OffsetDateTime horaFim;

    private Conteiner conteiner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ETipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(ETipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public OffsetDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(OffsetDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public OffsetDateTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(OffsetDateTime horaFim) {
        this.horaFim = horaFim;
    }

    public Conteiner getConteiner() {
        return conteiner;
    }

    public void setConteiner(Conteiner conteiner) {
        this.conteiner = conteiner;
    }
}
