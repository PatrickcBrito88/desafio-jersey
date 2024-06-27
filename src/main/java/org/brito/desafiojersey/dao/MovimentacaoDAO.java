package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.domain.Movimentacao;

import java.util.List;

/**
 * Interface para definir operações de banco de dados relacionadas às movimentações.
 */
public interface MovimentacaoDAO {

    /**
     * Cria uma nova movimentação no banco de dados.
     *
     * @param movimentacao {@link Movimentacao} a ser criada.
     * @return O ID gerado para a nova movimentação.
     */
    long criarMovimentacao(Movimentacao movimentacao);

    /**
     * Busca uma movimentação pelo seu ID.
     *
     * @param id O ID da movimentação.
     * @return A {@link Movimentacao} encontrada.
     */
    Movimentacao buscarMovimentacaoPorId(long id);

    /**
     * Fecha uma movimentação e atualiza a data/hora de fim.
     *
     * @param id O ID da movimentação a ser fechada.
     * @return A {@link Movimentacao} atualizada.
     */
    Movimentacao fecharMovimentacao(long id);

    /**
     * Lista todas as movimentações registradas.
     *
     * @return Uma lista de {@link Movimentacao}.
     */
    List<Movimentacao> listaMovimentacoes();

    /**
     * Lista todas as movimentações associadas a um contêiner específico.
     *
     * @param idConteiner ID do contêiner.
     * @return Uma lista de {@link Movimentacao} vinculadas ao contêiner especificado.
     */
    List<Movimentacao> listaMovimentacoesPorContainer(long idConteiner);

    /**
     * Lista todas as movimentações associadas a um cliente específico.
     *
     * @param idCliente ID do cliente.
     * @return Uma lista de {@link Movimentacao} vinculadas ao cliente especificado.
     */
    List<Movimentacao> listaMovimentacoesPorCliente(long idCliente);
}
