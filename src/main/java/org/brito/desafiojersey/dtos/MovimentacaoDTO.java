package org.brito.desafiojersey.dtos;

import org.brito.desafiojersey.enums.ETipoMovimentacao;

import java.time.OffsetDateTime;

public class MovimentacaoDTO {

    private ETipoMovimentacao tipo;

    private OffsetDateTime horaInicio;

    private OffsetDateTime horaFim;

    private ConteinerDTO conteiner;

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

    public ConteinerDTO getConteiner() {
        return conteiner;
    }

    public void setConteiner(ConteinerDTO conteiner) {
        this.conteiner = conteiner;
    }
}
