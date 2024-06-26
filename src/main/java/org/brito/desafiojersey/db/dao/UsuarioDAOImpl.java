package org.brito.desafiojersey.db.dao;

import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.dtos.UsuarioDTO;
import org.brito.desafiojersey.enums.ERole;
import org.brito.desafiojersey.exceptions.NaoEncontradoException;
import org.brito.desafiojersey.exceptions.UsuarioException;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.SqlLoaderUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.brito.desafiojersey.utils.CriptUtils.buscaPassCriptografado;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public String salvarUsuario(UsuarioDTO usuarioDTO) {
        String sql = SqlLoaderUtils.getSql("usuario.salvar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuarioDTO.getLogin());
            stmt.setString(2, buscaPassCriptografado(usuarioDTO.getPassword()));
            stmt.setString(3, usuarioDTO.getRole().getRole());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long chaveInserida = rs.getLong(1);
                return MessageUtils.buscaValidacao("usuario.salvo.sucesso", chaveInserida);
            } else {
                throw new UsuarioException(MessageUtils.buscaValidacao("usuario.erro.buscar.id"));
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("usuario.erro.salva", e.getMessage()));
        }
    }

    @Override
    public UsuarioDTO buscarUsuarioPorId(long id) {
        String sql = SqlLoaderUtils.getSql("usuario.buscar.por.id");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setId(rs.getLong("id"));
                usuarioDTO.setLogin(rs.getString("login"));
                usuarioDTO.setRole(ERole.toString(rs.getString("role")));
                return usuarioDTO;
            } else {
                throw new NaoEncontradoException(MessageUtils.buscaValidacao("usuario.nao.encontrado", id));
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("usuario.erro.buscar", e.getMessage()));
        }
    }

    @Override
    public UsuarioDTO editarUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public String deletarUsuario(Integer id) {
        return "";
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return List.of();
    }

}
