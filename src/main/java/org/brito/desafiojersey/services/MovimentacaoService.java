package org.brito.desafiojersey.services;

import org.brito.desafiojersey.dtos.MovimentacaoDTO;
import org.brito.desafiojersey.utils.Page;
import org.brito.desafiojersey.utils.PaginatedResponse;

import java.sql.SQLException;

/**
 * Interface de serviço para operações relacionadas a movimentações de contêineres.
 */
public interface MovimentacaoService {

    /**
     * Cria uma nova movimentação.
     *
     * @param movimentacaoDTO {@link MovimentacaoDTO} com os dados da movimentação.
     * @return Uma mensagem de sucesso.
     */
    String criarMovimentacao(MovimentacaoDTO movimentacaoDTO);

    /**
     * Busca uma movimentação pelo ID.
     *
     * @param id O ID da movimentação.
     * @return Um {@link MovimentacaoDTO} correspondente à movimentação encontrada.
     */
    MovimentacaoDTO buscarMovimentacaoPorId(long id);

    /**
     * Finaliza uma movimentação aberta, registrando a hora de término.
     *
     * @param id O ID da movimentação a ser finalizada.
     * @return Um {@link MovimentacaoDTO} atualizado.
     */
    MovimentacaoDTO fecharMovimentacao(long id);

    /**
     * Lista todas as movimentações de forma paginada.
     *
     * @param paginaAtual A página atual.
     * @param tamanhoPagina O tamanho da página.
     * @return Uma página de {@link MovimentacaoDTO}.
     */
    PaginatedResponse<MovimentacaoDTO> listaMovimentacoes(Integer paginaAtual, Integer tamanhoPagina) throws SQLException;

    /**
     * Lista todas as movimentações associadas a um contêiner específico de forma paginada.
     *
     * @param paginaAtual A página atual.
     * @param tamanhoPagina O tamanho da página.
     * @param idConteiner O ID do contêiner.
     * @return Uma página de {@link MovimentacaoDTO}.
     */
    Page<MovimentacaoDTO> listaMovimentacoesPorContainer(Integer paginaAtual, Integer tamanhoPagina, long idConteiner);

    /**
     * Lista todas as movimentações associadas a um cliente específico de forma paginada.
     *
     * @param paginaAtual A página atual.
     * @param tamanhoPagina O tamanho da página.
     * @param idCliente O ID do cliente.
     * @return Uma página de {@link MovimentacaoDTO}.
     */
    Page<MovimentacaoDTO> listaMovimentacoesPorCliente(Integer paginaAtual, Integer tamanhoPagina, long idCliente);

}
