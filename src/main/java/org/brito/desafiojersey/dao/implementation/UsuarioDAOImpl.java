package org.brito.desafiojersey.dao.implementation;

import org.brito.desafiojersey.dao.UsuarioDAO;
import org.brito.desafiojersey.dao.utils.SqlLoaderUtils;
import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.domain.Usuario;
import org.brito.desafiojersey.dtos.UsuarioDTO;
import org.brito.desafiojersey.enums.ERole;
import org.brito.desafiojersey.exceptions.NaoEncontradoException;
import org.brito.desafiojersey.exceptions.UsuarioException;
import org.brito.desafiojersey.utils.MessageUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.brito.desafiojersey.dao.utils.DaoUtils.buscarKeyGerada;
import static org.brito.desafiojersey.utils.CriptUtils.buscaPassCriptografado;

/**
 * Implementação da interface {@link UsuarioDAO} utilizando JDBC.
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public long salvarUsuario(UsuarioDTO usuarioDTO) throws UsuarioException {
        String sql = SqlLoaderUtils.getSql("usuario.salvar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preencherStatementSalvar(stmt, usuarioDTO);
            stmt.executeUpdate();
            return buscarKeyGerada(stmt);
        } catch (SQLException e) {
            throw new UsuarioException(e.getMessage());
        }
    }

    @Override
    public Usuario buscarUsuarioPorId(long id) throws UsuarioException {
        String sql = SqlLoaderUtils.getSql("usuario.buscar.por.id");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return executarQueryUsuario(stmt);
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("usuario.erro.buscar", e.getMessage()));
        }
    }

    @Override
    public Integer atualizarUsuario(UsuarioDTO usuarioDTO, Integer id) throws UsuarioException {
        String sql = SqlLoaderUtils.getSql("usuario.atualizar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            preencherStatementAtualizar(stmt, usuarioDTO, id);
            int linhasAtualizadas = stmt.executeUpdate();
            if (linhasAtualizadas == 0) {
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
    public void deletarUsuario(Integer id) throws UsuarioException {
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
    public List<Usuario> listarUsuarios() throws UsuarioException {
        String sql = SqlLoaderUtils.getSql("usuario.todos");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            return gerarListaUsuarios(rs);
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("usuario.erro.listar", e.getMessage()));
        }
    }

    private Usuario executarQueryUsuario(PreparedStatement stmt) throws SQLException, UsuarioException {
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return gerarUsuario(rs);
            } else {
                throw new NaoEncontradoException(
                        MessageUtils.buscaValidacao("usuario.nao.encontrado"));
            }
        }
    }

    private List<Usuario> gerarListaUsuarios(ResultSet rs) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        while (rs.next()) {
            usuarios.add(gerarUsuario(rs));
        }
        return usuarios;
    }

    private void preencherStatementSalvar(PreparedStatement stmt, UsuarioDTO usuarioDTO) throws SQLException {
        stmt.setString(1, usuarioDTO.getLogin());
        stmt.setString(2, buscaPassCriptografado(usuarioDTO.getPassword()));
        stmt.setString(3, usuarioDTO.getRole().getRole());
    }

    private void preencherStatementAtualizar(PreparedStatement stmt, UsuarioDTO usuarioDTO, long id) throws SQLException {
        stmt.setString(1, usuarioDTO.getLogin());
        stmt.setString(2, buscaPassCriptografado(usuarioDTO.getPassword()));
        stmt.setString(3, usuarioDTO.getRole().getRole());
        stmt.setLong(4, id);
    }

    private static Usuario gerarUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getLong("id"),
                rs.getString("login"),
                ERole.toString(rs.getString("role")));
    }
}
