package org.brito.desafiojersey.services.implementation;

import jakarta.inject.Inject;
import org.brito.desafiojersey.dao.UsuarioDAO;
import org.brito.desafiojersey.domain.Usuario;
import org.brito.desafiojersey.dtos.UsuarioDTO;
import org.brito.desafiojersey.services.UsuarioService;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.Page;
import org.brito.desafiojersey.utils.PaginadorUtils;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;
    private final ModelMapper modelMapper;

    @Inject
    public UsuarioServiceImpl(UsuarioDAO usuarioDAO, ModelMapper modelMapper) {
        this.usuarioDAO = usuarioDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public String salvarUsuario(UsuarioDTO usuarioDTO) {
        long idInserido = usuarioDAO.salvarUsuario(usuarioDTO);
        return MessageUtils.buscaValidacao("usuario.salvo.sucesso", idInserido);

    }

    @Override
    public UsuarioDTO buscarUsuarioPorId(long id) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);

        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Override
    public String atualizarUsuario(UsuarioDTO usuarioDTO, Integer id) {
        Integer idAtualizado = usuarioDAO.atualizarUsuario(usuarioDTO, id);
        return MessageUtils.buscaValidacao("usuario.editado.sucesso", idAtualizado);

    }

    @Override
    public String deletarUsuario(Integer id) {
        usuarioDAO.deletarUsuario(id);
        return MessageUtils.buscaValidacao("usuario.deletado.sucesso", id);
    }

    @Override
    public Page<UsuarioDTO> listarUsuariosPaginado(Integer paginaAtual, Integer tamanhoPagina) {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        List<UsuarioDTO> usuariosDTOs = usuarios.stream()
                .map(u -> modelMapper.map(u, UsuarioDTO.class))
                .toList();
        return PaginadorUtils.gerarPaginacao(usuariosDTOs, paginaAtual, tamanhoPagina);
    }

}
