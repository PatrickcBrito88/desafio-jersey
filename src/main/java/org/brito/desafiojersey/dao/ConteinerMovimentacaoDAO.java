package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.domain.ConteinerMovimentacao;

/**
 * Interface para definir operações de banco de dados relacionadas à movimentação de contêineres.
 */
public interface ConteinerMovimentacaoDAO {

    /**
     * Insere uma movimentação de contêiner no banco de dados.
     *
     * @param conteinerMovimentacao {@link ConteinerMovimentacao} contendo os dados da movimentação.
     */
    void insereConteineresMovimentacoes(ConteinerMovimentacao conteinerMovimentacao);

}
