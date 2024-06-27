package org.brito.desafiojersey.services;

import org.brito.desafiojersey.dtos.UsuarioDTO;
import org.brito.desafiojersey.utils.Page;

/**
 * Interface de serviço para operações relacionadas a usuários.
 */
public interface UsuarioService {

    /**
     * Salva um novo usuário no sistema.
     *
     * @param usuarioDTO {@link UsuarioDTO} com os dados do usuário.
     * @return Uma mensagem de sucesso.
     */
    String salvarUsuario(UsuarioDTO usuarioDTO);

    /**
     * Busca um usuário pelo ID.
     *
     * @param id O ID do usuário.
     * @return Um {@link UsuarioDTO} correspondente ao usuário encontrado.
     */
    UsuarioDTO buscarUsuarioPorId(long id);

    /**
     * Atualiza um usuário existente.
     *
     * @param usuarioDTO {@link UsuarioDTO} com os novos dados do usuário.
     * @param id O ID do usuário a ser atualizado.
     * @return Uma mensagem de sucesso.
     */
    String atualizarUsuario(UsuarioDTO usuarioDTO, Integer id);

    /**
     * Deleta um usuário do sistema.
     *
     * @param id O ID do usuário a ser deletado.
     * @return Uma mensagem de sucesso.
     */
    String deletarUsuario(Integer id);

    /**
     * Lista todos os usuários de forma paginada.
     *
     * @param paginaAtual A página atual.
     * @param tamanhoPagina O tamanho da página.
     * @return Uma página de {@link UsuarioDTO}.
     */
    Page<UsuarioDTO> listarUsuariosPaginado(Integer paginaAtual, Integer tamanhoPagina);

}
