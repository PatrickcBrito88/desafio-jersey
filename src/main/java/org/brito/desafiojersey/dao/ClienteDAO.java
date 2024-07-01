package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.domain.Cliente;
import org.brito.desafiojersey.dtos.ClienteDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para definir operações de banco de dados relacionadas a clientes.
 */
public interface ClienteDAO {

    /**
     * Salva um novo cliente no banco de dados.
     *
     * @param clienteDTO {@link ClienteDTO} contendo os dados do cliente.
     * @return O ID gerado para o cliente.
     */
    long salvarCliente(ClienteDTO clienteDTO);

    /**
     * Busca um cliente pelo ID.
     *
     * @param id O ID do cliente.
     * @return O {@link Cliente} encontrado, se existir.
     */
    Cliente buscarClientePorId(long id);

    /**
     * Atualiza os dados de um cliente existente.
     *
     * @param clienteDTO {@link ClienteDTO} contendo os novos dados do cliente.
     * @param id O ID do cliente a ser atualizado.
     * @return O ID do cliente atualizado.
     */
    Integer atualizarCliente(ClienteDTO clienteDTO, Integer id);

    /**
     * Exclui um cliente do banco de dados.
     *
     * @param id O ID do cliente a ser deletado.
     */
    void deletarCliente(Integer id);

    /**
     * Lista todos os clientes cadastrados no banco de dados.
     * @param paginaAtual a pagina atual que será exibida
     * @param tamanhoPagina o tamanho da página que retornará da consulta
     *
     * @return Uma lista de {@link Cliente}.
     */
    List<Cliente> listarClientes(Integer paginaAtual, Integer tamanhoPagina);

    /**
     * Conta o quantitativo de clientes no banco de dados
     * @return quantitativo de clientes no banco de dados
     */
    long buscarTotalClientes() throws SQLException;
}
