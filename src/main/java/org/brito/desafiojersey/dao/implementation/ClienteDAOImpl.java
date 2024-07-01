package org.brito.desafiojersey.dao.implementation;

import org.brito.desafiojersey.dao.ClienteDAO;
import org.brito.desafiojersey.dao.utils.SqlLoaderUtils;
import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.domain.Cliente;
import org.brito.desafiojersey.dtos.ClienteDTO;
import org.brito.desafiojersey.exceptions.ClienteException;
import org.brito.desafiojersey.exceptions.NaoEncontradoException;
import org.brito.desafiojersey.utils.MessageUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.brito.desafiojersey.dao.utils.DaoUtils.buscarKeyGerada;

/**
 * Implementação da interface {@link ClienteDAO} utilizando JDBC.
 */
public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public long salvarCliente(ClienteDTO clienteDTO) throws ClienteException {
        String sql = SqlLoaderUtils.getSql("cliente.salvar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, clienteDTO.getNome());
            stmt.executeUpdate();
            return buscarKeyGerada(stmt);
        } catch (SQLException e) {
            throw new ClienteException(MessageUtils.buscaValidacao("cliente.erro.salvar", e.getMessage()));
        }
    }

    @Override
    public Cliente buscarClientePorId(long id) throws ClienteException {
        String sql = SqlLoaderUtils.getSql("cliente.buscar.por.id");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return executarQueryClientes(stmt, id);
        } catch (SQLException e) {
            throw new ClienteException(MessageUtils.buscaValidacao("cliente.erro.buscar", e.getMessage()));
        }
    }

    @Override
    public Integer atualizarCliente(ClienteDTO clienteDTO, Integer id) throws ClienteException {
        String sql = SqlLoaderUtils.getSql("cliente.atualizar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clienteDTO.getNome());
            stmt.setLong(2, id);
            int linhasAtualizadas = stmt.executeUpdate();
            if (linhasAtualizadas == 0) {
                throw new NaoEncontradoException(MessageUtils.buscaValidacao("cliente.nao.encontrado", id));
            }
            return id;
        } catch (SQLException e) {
            throw new ClienteException(MessageUtils.buscaValidacao("cliente.erro.atualizar", e.getMessage()));
        }
    }

    @Override
    public void deletarCliente(Integer id) throws ClienteException {
        String sql = SqlLoaderUtils.getSql("cliente.deletar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasDeletadas = stmt.executeUpdate();
            if (linhasDeletadas == 0) {
                throw new NaoEncontradoException(MessageUtils.buscaValidacao("cliente.nao.encontrado", id));
            }
        } catch (SQLException e) {
            throw new ClienteException(MessageUtils.buscaValidacao("cliente.erro.deletar", e.getMessage()));
        }
    }

    @Override
    public List<Cliente> listarClientes(Integer paginaAtual, Integer tamanhoPagina) throws ClienteException {
        String sql = SqlLoaderUtils.getSql("cliente.todos");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setInt(1, tamanhoPagina);
             stmt.setInt(2, paginaAtual);
             try(ResultSet rs = stmt.executeQuery()) {
                 List<Cliente> clientes = new ArrayList<>();
                 while (rs.next()) {
                     Cliente cliente = gerarCliente(rs);
                     clientes.add(cliente);
                 }
                 return clientes;
             }

        } catch (SQLException e) {
            throw new ClienteException(MessageUtils.buscaValidacao("cliente.erro.listar", e.getMessage()));
        }
    }

    @Override
    public long buscarTotalClientes() throws SQLException {
        String sql = SqlLoaderUtils.getSql("cliente.total");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            if (rs.next()){
                return rs.getLong(1);
            } else {
                throw new ClienteException(MessageUtils.buscaValidacao("erro.buscar.total"));
            }
        }
    }


    private Cliente executarQueryClientes(PreparedStatement stmt, long id) throws SQLException, ClienteException {
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return gerarCliente(rs);
            } else {
                throw new NaoEncontradoException(MessageUtils.buscaValidacao("cliente.nao.encontrado", id));
            }
        }
    }

    private List<Cliente> gerarListaClientes(ResultSet rs) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            clientes.add(gerarCliente(rs));
        }
        return clientes;
    }

    private static Cliente gerarCliente(ResultSet rs) throws SQLException {
        return new Cliente(rs.getLong("id"), rs.getString("nome"));
    }
}
