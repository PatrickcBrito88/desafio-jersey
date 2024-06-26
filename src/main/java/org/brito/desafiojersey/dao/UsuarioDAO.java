package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.domain.Usuario;
import org.brito.desafiojersey.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioDAO {

    long salvarUsuario(UsuarioDTO usuarioDTO);

    Usuario buscarUsuarioPorId(long id);

    Integer atualizarUsuario(UsuarioDTO usuarioDTO, Integer id);

    void deletarUsuario(Integer id);

    List<Usuario> listarUsuarios();
}
