package org.brito.desafiojersey.services;

import org.brito.desafiojersey.dtos.ConteinerDTO;
import org.brito.desafiojersey.dtos.ConteinerUsuarioDTO;
import org.brito.desafiojersey.utils.PaginatedResponse;

import java.sql.SQLException;

/**
 * Interface de serviço para operações relacionadas a contêineres.
 */
public interface ConteinerService {

    /**
     * Salva um novo contêiner.
     *
     * @param conteinerDTO {@link ConteinerDTO} com os dados do contêiner.
     * @return Uma mensagem de sucesso.
     */
    String salvarConteiner(ConteinerDTO conteinerDTO);

    /**
     * Busca um contêiner pelo ID.
     *
     * @param id O ID do contêiner.
     * @return Um {@link ConteinerDTO} correspondente ao contêiner encontrado.
     */
    ConteinerDTO buscarConteinerPorId(long id);

    /**
     * Atualiza um contêiner existente.
     *
     * @param conteinerDTO {@link ConteinerDTO} com os novos dados do contêiner.
     * @param id O ID do contêiner a ser atualizado.
     * @return Uma mensagem de sucesso.
     */
    String atualizarConteiner(ConteinerDTO conteinerDTO, Integer id);

    /**
     * Deleta um contêiner.
     *
     * @param id O ID do contêiner a ser deletado.
     * @return Uma mensagem de sucesso.
     */
    String deletarConteiner(Integer id);

    /**
     * Lista todos os contêineres de forma paginada.
     *
     * @param paginaAtual A página atual.
     * @param tamanhoPagina O tamanho da página.
     *
     * @return Uma página de {@link ConteinerDTO}.
     */
    PaginatedResponse<ConteinerDTO> listarConteinersPaginados(Integer paginaAtual, Integer tamanhoPagina) throws SQLException;

    /**
     * Lista todos os contêineres associados a um cliente.
     *
     * @param idCliente ID do cliente.
     * @return Um {@link ConteinerUsuarioDTO} contendo o cliente e seus contêineres.
     */
    ConteinerUsuarioDTO listaConteineresPorCliente(Integer idCliente);

}
