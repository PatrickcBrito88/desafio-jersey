package org.brito.desafiojersey.db.dao;

import org.brito.desafiojersey.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioDAO {

    String salvarUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO buscarUsuarioPorId(long id);

    UsuarioDTO editarUsuario(UsuarioDTO usuarioDTO);

    String deletarUsuario(Integer id);

    List<UsuarioDTO> listarUsuarios();
}
