package org.brito.desafiojersey.services;

import org.brito.desafiojersey.dtos.ClienteDTO;
import org.brito.desafiojersey.utils.Page;

/**
 * Interface de serviço para operações relacionadas a clientes.
 */
public interface ClienteService {

    /**
     * Salva um novo cliente.
     *
     * @param clienteDTO {@link ClienteDTO} com os dados do cliente.
     * @return Uma mensagem indicando sucesso.
     */
    String salvarCliente(ClienteDTO clienteDTO);

    /**
     * Busca um cliente por ID.
     *
     * @param id O ID do cliente.
     * @return Um {@link ClienteDTO} correspondente ao cliente encontrado.
     */
    ClienteDTO buscarClientePorId(long id);

    /**
     * Atualiza os dados de um cliente existente.
     *
     * @param clienteDTO {@link ClienteDTO} com os novos dados do cliente.
     * @param id O ID do cliente a ser atualizado.
     * @return Uma mensagem indicando sucesso.
     */
    String atualizarCliente(ClienteDTO clienteDTO, Integer id);

    /**
     * Deleta um cliente.
     *
     * @param id O ID do cliente a ser deletado.
     * @return Uma mensagem indicando sucesso.
     */
    String deletarCliente(Integer id);

    /**
     * Lista os clientes de forma paginada.
     *
     * @param paginaAtual O número da página atual.
     * @param tamanhoPagina O número de registros por página.
     * @return Uma página de {@link ClienteDTO}.
     */
    Page<ClienteDTO> listarClientesPaginados(Integer paginaAtual, Integer tamanhoPagina);

}
