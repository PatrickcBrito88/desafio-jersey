package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.domain.Usuario;
import org.brito.desafiojersey.dtos.UsuarioDTO;

import java.util.List;

/**
 * Interface para definir operações de banco de dados relacionadas aos usuários.
 */
public interface UsuarioDAO {

    /**
     * Salva um novo usuário no banco de dados.
     *
     * @param usuarioDTO {@link UsuarioDTO} com os dados do usuário.
     * @return O ID gerado para o novo usuário.
     */
    long salvarUsuario(UsuarioDTO usuarioDTO);

    /**
     * Busca um usuário pelo ID.
     *
     * @param id O ID do usuário.
     * @return O {@link Usuario} correspondente, se encontrado.
     */
    Usuario buscarUsuarioPorId(long id);

    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param usuarioDTO {@link UsuarioDTO} com os novos dados do usuário.
     * @param id O ID do usuário a ser atualizado.
     * @return O ID do usuário atualizado.
     */
    Integer atualizarUsuario(UsuarioDTO usuarioDTO, Integer id);

    /**
     * Deleta um usuário do banco de dados.
     *
     * @param id O ID do usuário a ser deletado.
     */
    void deletarUsuario(Integer id);

    /**
     * Lista todos os usuários cadastrados no banco de dados.
     *
     * @return Uma lista de {@link Usuario}.
     */
    List<Usuario> listarUsuarios();
}
