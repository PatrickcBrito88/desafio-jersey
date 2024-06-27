package org.brito.desafiojersey.domain;

import org.brito.desafiojersey.enums.ETipoMovimentacao;

import java.time.LocalDateTime;

public class Movimentacao {

    private Long id;

    private ETipoMovimentacao tipo;

    private LocalDateTime horaInicio;

    private LocalDateTime horaFim;

    private Conteiner conteiner;

    public Movimentacao() {
    }

    public Movimentacao(Long id, ETipoMovimentacao tipo, LocalDateTime horaInicio, LocalDateTime horaFim, Conteiner conteiner) {
        this.id = id;
        this.tipo = tipo;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.conteiner = conteiner;
    }

    public Movimentacao(Long id, ETipoMovimentacao tipo, Conteiner conteiner) {
        this.id = id;
        this.tipo = tipo;
        this.horaInicio = LocalDateTime.now();;
        this.horaFim = null;
        this.conteiner = conteiner;
    }

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

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalDateTime horaFim) {
        this.horaFim = horaFim;
    }

    public Conteiner getConteiner() {
        return conteiner;
    }

    public void setConteiner(Conteiner conteiner) {
        this.conteiner = conteiner;
    }

}
