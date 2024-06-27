package org.brito.desafiojersey.dao.implementation;

import jakarta.inject.Inject;
import org.brito.desafiojersey.dao.ConteineresMovimentacoesDAO;
import org.brito.desafiojersey.dao.MovimentacaoDAO;
import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.domain.Conteiner;
import org.brito.desafiojersey.domain.ConteineresMovimentacoes;
import org.brito.desafiojersey.domain.Movimentacao;
import org.brito.desafiojersey.enums.ETipoMovimentacao;
import org.brito.desafiojersey.exceptions.MovimentacaoException;
import org.brito.desafiojersey.exceptions.NaoEncontradoException;
import org.brito.desafiojersey.exceptions.UsuarioException;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.SqlLoaderUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDAOImpl implements MovimentacaoDAO {

    private final ConteineresMovimentacoesDAO conteineresMovimentacoesDAO;

    @Inject
    public MovimentacaoDAOImpl(ConteineresMovimentacoesDAO conteineresMovimentacoesDAO) {
        this.conteineresMovimentacoesDAO = conteineresMovimentacoesDAO;
    }

    @Override
    public long criarMovimentacao(Movimentacao movimentacao) {
        String sql = SqlLoaderUtils.getSql("movimentacao.criar");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, movimentacao.getTipo().name());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(movimentacao.getHoraInicio()));
            stmt.setTimestamp(3, movimentacao.getHoraFim() == null ? null : java.sql.Timestamp.valueOf(movimentacao.getHoraFim()));
            stmt.setLong(4, movimentacao.getConteiner().getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long idInserido = rs.getLong(1);
                conteineresMovimentacoesDAO.insereConteineresMovimentacoes(new ConteineresMovimentacoes(movimentacao.getConteiner().getId(), idInserido));
                return idInserido;
            } else {
                throw new MovimentacaoException(MessageUtils.buscaValidacao("movimentacao.conteiner.erro"));
            }
        } catch (SQLException e) {
            throw new MovimentacaoException(
                    MessageUtils.buscaValidacao("movimentacao.erro.criacao", e.getMessage()));
        }
    }

    @Override
    public Movimentacao buscarMovimentacaoPorId(long id) {
        String sql = SqlLoaderUtils.getSql("movimentacao.buscar.por.id");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return gerarMovimentacao(rs);
            } else {
                throw new NaoEncontradoException(MessageUtils.buscaValidacao("movimentacao.nao.encontrado", id));
            }
        } catch (SQLException e) {
            throw new MovimentacaoException(
                    MessageUtils.buscaValidacao("movimentacao.erro.buscar", e.getMessage()));
        }
    }

    @Override
    public Movimentacao fecharMovimentacao(long id) {
        String atualizaSql = SqlLoaderUtils.getSql("movimentacao.fecha");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement atualizaStmt = conn.prepareStatement(atualizaSql)) {
            atualizaStmt.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            atualizaStmt.setLong(2, id);
            int rowsAffected = atualizaStmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new MovimentacaoException(MessageUtils.buscaValidacao("movimentacao.nao.encontrada", id));
            }
            return buscarMovimentacaoPorId(id);
        } catch (SQLException e) {
            throw new MovimentacaoException(MessageUtils.buscaValidacao("movimentacao.erro.buscar", e.getMessage()));
        }
    }

    @Override
    public List<Movimentacao> listaMovimentacoes() {
        String sql = SqlLoaderUtils.getSql("movimentacao.todos");
        List<Movimentacao> movimentacoes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Movimentacao movimentacao = gerarMovimentacao(rs);
                movimentacoes.add(movimentacao);
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("movimentacao.erro.listar", e.getMessage()));
        }
        return movimentacoes;
    }


    @Override
    public List<Movimentacao> listaMovimentacoesPorContainer(long idConteiner) {
        String sql = SqlLoaderUtils.getSql("movimentacao.todos.por.container");
        List<Movimentacao> movimentacoes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idConteiner);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Movimentacao movimentacao = gerarMovimentacao(rs);
                    movimentacoes.add(movimentacao);
                }
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("movimentacao.erro.listar", e.getMessage()));
        }
        return movimentacoes;
    }

    @Override
    public List<Movimentacao> listaMovimentacoesPorCliente(long idCliente) {
        String sql = SqlLoaderUtils.getSql("movimentacao.todos.por.cliente");
        List<Movimentacao> movimentacoes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Movimentacao movimentacao = gerarMovimentacao(rs);
                    movimentacoes.add(movimentacao);
                }
            }
        } catch (SQLException e) {
            throw new UsuarioException(
                    MessageUtils.buscaValidacao("movimentacao.erro.listar", e.getMessage()));
        }
        return movimentacoes;
    }

    private static Movimentacao gerarMovimentacao(ResultSet rs) throws SQLException {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setId(rs.getLong("id"));
        movimentacao.setHoraFim(rs.getTimestamp("hora_fim").toLocalDateTime());
        movimentacao.setHoraInicio(rs.getTimestamp("hora_inicio").toLocalDateTime());

        Conteiner conteiner = new Conteiner();
        conteiner.setId(rs.getLong("conteiner_id"));

        movimentacao.setConteiner(conteiner);
        movimentacao.setTipo(ETipoMovimentacao.valueOf(rs.getString("tipo")));

        return movimentacao;
    }
}
