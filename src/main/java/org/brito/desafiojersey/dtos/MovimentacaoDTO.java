package org.brito.desafiojersey.dtos;

import jakarta.validation.constraints.NotNull;
import org.brito.desafiojersey.enums.ETipoMovimentacao;

import java.time.LocalDateTime;

public class MovimentacaoDTO {

    private Long id;

    private ETipoMovimentacao tipo;

    private LocalDateTime horaInicio;

    private LocalDateTime horaFim;

    @NotNull(message = "{movimentacao.container.nulo}")
    private ConteinerDTO conteiner;

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

    public ConteinerDTO getConteiner() {
        return conteiner;
    }

    public void setConteiner(ConteinerDTO conteiner) {
        this.conteiner = conteiner;
    }
}
