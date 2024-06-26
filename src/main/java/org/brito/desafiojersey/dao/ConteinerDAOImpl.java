package org.brito.desafiojersey.dao;

import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.domain.Cliente;
import org.brito.desafiojersey.domain.Conteiner;
import org.brito.desafiojersey.enums.ECategoria;
import org.brito.desafiojersey.enums.EStatus;
import org.brito.desafiojersey.enums.ETipoConteiner;
import org.brito.desafiojersey.exceptions.ConteinerException;
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

public class ConteinerDAOImpl implements ConteinerDAO {

    @Override
    public long salvarContainer(Conteiner conteiner) {
        String sql = SqlLoaderUtils.getSql("conteiner.salvar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, conteiner.getIdentificacao());
            pstmt.setLong(2, conteiner.getCliente().getId());
            pstmt.setString(3, conteiner.getTipo().toString());
            pstmt.setString(4, conteiner.getCategoria().toString());
            pstmt.setString(5, conteiner.getStatus().toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new UsuarioException(MessageUtils.buscaValidacao("database.erro.buscar.id"));
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("conteiner.erro.salva", e.getMessage()));
        }
    }

    @Override
    public Conteiner buscarContainerPorId(long id) {
        String sql = SqlLoaderUtils.getSql("conteiner.buscar.por.id");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return gerarContainer(rs);
            } else {
                throw new NaoEncontradoException(MessageUtils.buscaValidacao("conteiner.nao.encontrado", id));
            }
        } catch (SQLException e) {
            throw new ConteinerException(
                    MessageUtils.buscaValidacao("conteiner.erro.buscar", e.getMessage()));
        }
    }

    private static Conteiner gerarContainer(ResultSet rs) throws SQLException {
        Conteiner conteiner = new Conteiner();
        conteiner.setId(rs.getLong("id"));
        conteiner.setIdentificacao(rs.getString("identificacao"));

        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("cliente_id"));

        conteiner.setCliente(cliente);
        conteiner.setTipo(ETipoConteiner.valueOf(rs.getString("tipo")));
        conteiner.setCategoria(ECategoria.valueOf(rs.getString("categoria")));
        conteiner.setStatus(EStatus.valueOf(rs.getString("status")));

        return conteiner;
    }

    @Override
    public Integer atualizarContainer(Conteiner conteiner, Integer id) {
        String sql = SqlLoaderUtils.getSql("conteiner.atualizar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, conteiner.getIdentificacao());
            stmt.setLong(2, conteiner.getCliente().getId());
            stmt.setString(3, conteiner.getTipo().toString());
            stmt.setString(4, conteiner.getCategoria().toString());
            stmt.setString(5, conteiner.getStatus().toString());
            stmt.setInt(6, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new NaoEncontradoException(
                        MessageUtils.buscaValidacao("conteiner.nao.encontrado", id));
            }
            return id;
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("conteiner.erro.atualizar", e.getMessage()));
        }
    }

    @Override
    public void deletarContainer(Integer id) {
        String sql = SqlLoaderUtils.getSql("conteiner.deletar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new NaoEncontradoException(
                        MessageUtils.buscaValidacao("conteiner.nao.encontrado", id));
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("conteiner.erro.deletar", e.getMessage()));
        }
    }

    @Override
    public List<Conteiner> listarContaineres() {
        String sql = SqlLoaderUtils.getSql("conteiner.todos");
        List<Conteiner> conteiners = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Conteiner conteiner = gerarContainer(rs);
                conteiners.add(conteiner);
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("conteiner.erro.listar", e.getMessage()));
        }
        return conteiners;
    }

    @Override
    public List<Conteiner> listaConteineresPorCliente(long idCliente) {
        String sql = SqlLoaderUtils.getSql("conteiner.por.cliente");
        List<Conteiner> conteiners = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Conteiner conteiner = gerarContainer(rs);
                    conteiners.add(conteiner);
                }
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("conteiner.erro.listar", e.getMessage()));
        }
        return conteiners;
    }


}
