package org.brito.desafiojersey.services;

import org.brito.desafiojersey.dtos.MovimentacaoDTO;
import org.brito.desafiojersey.utils.Page;

public interface MovimentacaoService {

    String criarMovimentacao(MovimentacaoDTO movimentacaoDTO);

    MovimentacaoDTO buscarMovimentacaoPorId(long id);

    MovimentacaoDTO fecharMovimentacao(long id);

    Page<MovimentacaoDTO> listaMovimentacoes(Integer paginaAtual, Integer tamanhoPagina);

    Page<MovimentacaoDTO> listaMovimentacoesPorContainer(Integer paginaAtual, Integer tamanhoPagina, long idConteiner);

    Page<MovimentacaoDTO> listaMovimentacoesPorCliente(Integer paginaAtual, Integer tamanhoPagina, long idCliente);

}
