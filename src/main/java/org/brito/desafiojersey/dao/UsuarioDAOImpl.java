package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.domain.Usuario;
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
import java.util.ArrayList;
import java.util.List;

import static org.brito.desafiojersey.utils.CriptUtils.buscaPassCriptografado;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public long salvarUsuario(UsuarioDTO usuarioDTO) {
        String sql = SqlLoaderUtils.getSql("usuario.salvar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuarioDTO.getLogin());
            stmt.setString(2, buscaPassCriptografado(usuarioDTO.getPassword()));
            stmt.setString(3, usuarioDTO.getRole().getRole());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new UsuarioException(MessageUtils.buscaValidacao("database.erro.buscar.id"));
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("usuario.erro.salva", e.getMessage()));
        }
    }

    @Override
    public Usuario buscarUsuarioPorId(long id) {
        String sql = SqlLoaderUtils.getSql("usuario.buscar.por.id");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return gerarUsuario(rs);
            } else {
                throw new NaoEncontradoException(MessageUtils.buscaValidacao("usuario.nao.encontrado", id));
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("usuario.erro.buscar", e.getMessage()));
        }
    }

    private static Usuario gerarUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setLogin(rs.getString("login"));
        usuario.setRole(ERole.toString(rs.getString("role")));
        return usuario;
    }

    @Override
    public Integer atualizarUsuario(UsuarioDTO usuarioDTO, Integer id) {
        String sql = SqlLoaderUtils.getSql("usuario.atualizar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuarioDTO.getLogin());
            stmt.setString(2, buscaPassCriptografado(usuarioDTO.getPassword()));
            stmt.setString(3, usuarioDTO.getRole().getRole());
            stmt.setLong(4, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new NaoEncontradoException(
                        MessageUtils.buscaValidacao("usuario.nao.encontrado", id));
            }
            return id;
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("usuario.erro.atualizar", e.getMessage()));
        }
    }

    @Override
    public void deletarUsuario(Integer id) {
        String sql = SqlLoaderUtils.getSql("usuario.deletar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new NaoEncontradoException(
                        MessageUtils.buscaValidacao("usuario.nao.encontrado", id));
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("usuario.erro.deletar", e.getMessage()));
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        String sql = SqlLoaderUtils.getSql("usuario.todos");
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = gerarUsuario(rs);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("usuario.erro.listar", e.getMessage()));
        }
        return usuarios;
    }

}
