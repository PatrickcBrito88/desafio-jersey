package org.brito.desafiojersey.dao.implementation;

import jakarta.inject.Inject;
import org.brito.desafiojersey.dao.ClienteDAO;
import org.brito.desafiojersey.dao.ConteinerDAO;
import org.brito.desafiojersey.dao.utils.SqlLoaderUtils;
import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.domain.Cliente;
import org.brito.desafiojersey.domain.Conteiner;
import org.brito.desafiojersey.enums.ECategoria;
import org.brito.desafiojersey.enums.EStatus;
import org.brito.desafiojersey.enums.ETipoConteiner;
import org.brito.desafiojersey.exceptions.ConteinerException;
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
 * Implementação da interface {@link ConteinerDAO} utilizando JDBC e injeção de dependências.
 */
public class ConteinerDAOImpl implements ConteinerDAO {

    private final ClienteDAO clienteDAO;

    @Inject
    public ConteinerDAOImpl(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public long salvarContainer(Conteiner conteiner) throws ConteinerException {
        String sql = SqlLoaderUtils.getSql("conteiner.salvar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preencherStatementSalvar(stmt, conteiner);
            stmt.executeUpdate();
            return buscarKeyGerada(stmt);
        } catch (SQLException e) {
            throw new ConteinerException(
                    MessageUtils.buscaValidacao("conteiner.erro.salvar", e.getMessage()));
        }
    }

    @Override
    public Conteiner buscarContainerPorId(long id) throws ConteinerException {
        String sql = SqlLoaderUtils.getSql("conteiner.buscar.por.id");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return executarQueryConteiner(stmt, id);
        } catch (SQLException e) {
            throw new ConteinerException(
                    MessageUtils.buscaValidacao("conteiner.erro.buscar", e.getMessage()));
        }
    }

    @Override
    public Integer atualizarContainer(Conteiner conteiner, Integer id) throws ConteinerException {
        String sql = SqlLoaderUtils.getSql("conteiner.atualizar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            preencherStatementAtualizar(stmt, conteiner, id);
            int linhasAtualizadas = stmt.executeUpdate();
            if (linhasAtualizadas == 0) {
                throw new NaoEncontradoException(
                        MessageUtils.buscaValidacao("conteiner.nao.encontrado", id));
            }
            return id;
        } catch (SQLException e) {
            throw new ConteinerException(
                    MessageUtils.buscaValidacao("conteiner.erro.atualizar", e.getMessage()));
        }
    }

    @Override
    public void deletarContainer(Integer id) throws ConteinerException {
        String sql = SqlLoaderUtils.getSql("conteiner.deletar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasDeletadas = stmt.executeUpdate();
            if (linhasDeletadas == 0) {
                throw new NaoEncontradoException(
                        MessageUtils.buscaValidacao("conteiner.nao.encontrado", id));
            }
        } catch (SQLException e) {
            throw new ConteinerException(MessageUtils.buscaValidacao("conteiner.erro.deletar", e.getMessage()));
        }
    }

    @Override
    public List<Conteiner> listarContaineres() throws ConteinerException {
        String sql = SqlLoaderUtils.getSql("conteiner.todos");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            return gerarListaConteineres(rs);
        } catch (SQLException e) {
            throw new ConteinerException(
                    MessageUtils.buscaValidacao("conteiner.erro.listar", e.getMessage()));
        }
    }

    @Override
    public List<Conteiner> listaConteineresPorCliente(long idCliente) throws ConteinerException {
        String sql = SqlLoaderUtils.getSql("conteiner.por.cliente");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idCliente);
            return executarQueryListaConteineres(stmt);
        } catch (SQLException e) {
            throw new ConteinerException(
                    MessageUtils.buscaValidacao("conteiner.erro.listar.por.cliente", e.getMessage()));
        }
    }

    private Conteiner executarQueryConteiner(PreparedStatement stmt, long id) throws SQLException, ConteinerException {
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return gerarContainer(rs);
            } else {
                throw new NaoEncontradoException(
                        MessageUtils.buscaValidacao("conteiner.nao.encontrado", id));
            }
        }
    }

    private List<Conteiner> gerarListaConteineres(ResultSet rs) throws SQLException, ConteinerException {
        List<Conteiner> conteiners = new ArrayList<>();
        while (rs.next()) {
            conteiners.add(gerarContainer(rs));
        }
        return conteiners;
    }

    private void preencherStatementSalvar(PreparedStatement stmt, Conteiner conteiner) throws SQLException {
        stmt.setString(1, conteiner.getIdentificacao());
        stmt.setLong(2, conteiner.getCliente().getId());
        stmt.setString(3, conteiner.getTipo().toString());
        stmt.setString(4, conteiner.getCategoria().toString());
        stmt.setString(5, conteiner.getStatus().toString());
    }

    private void preencherStatementAtualizar(PreparedStatement stmt, Conteiner conteiner, long id) throws SQLException {
        preencherStatementSalvar(stmt, conteiner);
        stmt.setLong(6, id);
    }

    private Conteiner gerarContainer(ResultSet rs) throws SQLException, ConteinerException {
        long id = rs.getLong("id");
        String identificacao = rs.getString("identificacao");
        long clienteId = rs.getLong("cliente_id");
        Cliente cliente = clienteDAO.buscarClientePorId(clienteId);
        ETipoConteiner tipo = ETipoConteiner.valueOf(rs.getString("tipo"));
        ECategoria categoria = ECategoria.valueOf(rs.getString("categoria"));
        EStatus status = EStatus.valueOf(rs.getString("status"));

        return new Conteiner(id, identificacao, cliente, tipo, categoria, status);
    }

    private List<Conteiner> executarQueryListaConteineres(PreparedStatement stmt) throws SQLException, ConteinerException {
        try (ResultSet rs = stmt.executeQuery()) {
            List<Conteiner> conteiners = new ArrayList<>();
            while (rs.next()) {
                conteiners.add(gerarContainer(rs));
            }
            return conteiners;
        }
    }

}
