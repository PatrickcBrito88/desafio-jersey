package org.brito.desafiojersey.services;

import org.brito.desafiojersey.dtos.UsuarioDTO;
import org.brito.desafiojersey.utils.Page;

public interface UsuarioService {

    String salvarUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO buscarUsuarioPorId(long id);

    String atualizarUsuario(UsuarioDTO usuarioDTO, Integer id);

    String deletarUsuario(Integer id);

    Page<UsuarioDTO> listarUsuariosPaginado(Integer paginaAtual, Integer tamanhoPagina);

}
