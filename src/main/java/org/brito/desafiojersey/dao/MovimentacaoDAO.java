package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.domain.Movimentacao;

import java.util.List;

public interface MovimentacaoDAO {

    long criarMovimentacao(Movimentacao movimentacao);

    Movimentacao buscarMovimentacaoPorId(long id);

    Movimentacao fecharMovimentacao(long id);

    List<Movimentacao> listaMovimentacoes();

    List<Movimentacao> listaMovimentacoesPorContainer(long idConteiner);

    List<Movimentacao> listaMovimentacoesPorCliente(long idCliente);
}
