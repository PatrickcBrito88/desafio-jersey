package org.brito.desafiojersey.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.brito.desafiojersey.enums.ECategoria;
import org.brito.desafiojersey.enums.EStatus;
import org.brito.desafiojersey.enums.ETipoConteiner;

import java.util.ArrayList;
import java.util.List;

public class ConteinerDTO {

    private Long id;

    @NotBlank(message = "{conteiner.identificacao.em.branco}")
    private String identificacao;

    @NotNull(message = "{conteiner.cliente.nulo}")
    private ClienteDTO cliente;

    @NotNull(message = "Erro no enum")
    private ETipoConteiner tipo;

    private ECategoria categoria;

    private EStatus status;

    private List<MovimentacaoDTO> movimentacoes = new ArrayList<>();

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

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
