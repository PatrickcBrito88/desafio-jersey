package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.domain.Conteiner;

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
     *
     * @return Uma lista de {@link Conteiner}.
     */
    List<Conteiner> listarContaineres();

    /**
     * Lista contêineres associados a um determinado cliente.
     *
     * @param idCliente ID do cliente para o qual os contêineres são buscados.
     * @return Uma lista de {@link Conteiner}.
     */
    List<Conteiner> listaConteineresPorCliente(long idCliente);
}
