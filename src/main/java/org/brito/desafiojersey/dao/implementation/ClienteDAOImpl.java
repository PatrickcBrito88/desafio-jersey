package org.brito.desafiojersey.dao.implementation;

import org.brito.desafiojersey.dao.ClienteDAO;
import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.domain.Cliente;
import org.brito.desafiojersey.dtos.ClienteDTO;
import org.brito.desafiojersey.exceptions.ClienteException;
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

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public long salvarCliente(ClienteDTO clienteDTO) {
        String sql = SqlLoaderUtils.getSql("cliente.salvar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clienteDTO.getNome());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new ClienteException(MessageUtils.buscaValidacao("database.erro.buscar.id"));
            }
        } catch (SQLException e) {
            throw new ClienteException(
                    MessageUtils.buscaValidacao("cliente.erro.salva", e.getMessage()));
        }
    }

    @Override
    public Cliente buscarClientePorId(long id) {
        String sql = SqlLoaderUtils.getSql("cliente.buscar.por.id");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return gerarCliente(rs);
            } else {
                throw new NaoEncontradoException(MessageUtils.buscaValidacao("cliente.nao.encontrado", id));
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("cliente.erro.buscar", e.getMessage()));
        }
    }

    private static Cliente gerarCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id"));
        cliente.setNome(rs.getString("nome"));
        return cliente;
    }

    @Override
    public Integer atualizarCliente(ClienteDTO clienteDTO, Integer id) {
        String sql = SqlLoaderUtils.getSql("cliente.atualizar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clienteDTO.getNome());
            stmt.setLong(2, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new NaoEncontradoException(
                        MessageUtils.buscaValidacao("cliente.nao.encontrado", id));
            }
            return id;
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("cliente.erro.atualizar", e.getMessage()));
        }
    }

    @Override
    public void deletarCliente(Integer id) {
        String sql = SqlLoaderUtils.getSql("cliente.deletar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new NaoEncontradoException(
                        MessageUtils.buscaValidacao("cliente.nao.encontrado", id));
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("cliente.erro.deletar", e.getMessage()));
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        String sql = SqlLoaderUtils.getSql("cliente.todos");
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = gerarCliente(rs);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("usuario.erro.listar", e.getMessage()));
        }
        return clientes;
    }

}
