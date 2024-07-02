package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.domain.Conteiner;
import org.brito.desafiojersey.exceptions.ConteinerException;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para definir operações de banco de dados relacionadas a contêineres.
 */
public interface ConteinerDAO {

    /**
     * Salva um novo contêiner no banco de dados.
     *
     * @param conteiner {@link Conteiner} contendo os dados do contêiner.
     * @return O ID gerado para o contêiner.
     */
    long salvarContainer(Conteiner conteiner);

    /**
     * Busca um contêiner pelo ID.
     *
     * @param id O ID do contêiner.
     * @return O {@link Conteiner} encontrado, se existir.
     */
    Conteiner buscarContainerPorId(long id);

    /**
     * Atualiza os dados de um contêiner existente.
     *
     * @param conteiner {@link Conteiner} contendo os novos dados do contêiner.
     * @param id O ID do contêiner a ser atualizado.
     * @return O ID do contêiner atualizado.
     */
    Integer atualizarContainer(Conteiner conteiner, Integer id);

    /**
     * Exclui um contêiner do banco de dados.
     *
     * @param id O ID do contêiner a ser deletado.
     */
    void deletarContainer(Integer id);

    /**
     * Lista todos os contêineres cadastrados no banco de dados.
     * @param paginaAtual a pagina atual a ser exibida
     * @param tamanhoPagina o tamanho da paginação
     *
     * @return Uma lista de {@link Conteiner}.
     */
    List<Conteiner> listarContaineres(Integer paginaAtual, Integer tamanhoPagina) throws ConteinerException, SQLException;

    /**
     * Lista contêineres associados a um determinado cliente.
     *
     * @param idCliente ID do cliente para o qual os contêineres são buscados.
     * @return Uma lista de {@link Conteiner}.
     */
    List<Conteiner> listaConteineresPorCliente(long idCliente);

    /**
     * Busca a quantidade total de itens na tabela
     *
     * @return a quantidade total de itens na tabela
     */
    long buscarQuantidadeTotalTabela();
}
