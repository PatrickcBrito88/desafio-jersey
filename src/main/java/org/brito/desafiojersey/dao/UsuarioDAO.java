package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioDAO {

    String salvarUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO buscarUsuarioPorId(long id);

    String atualizarUsuario(UsuarioDTO usuarioDTO, Integer id);

    String deletarUsuario(Integer id);

    List<UsuarioDTO> listarUsuarios();
}
