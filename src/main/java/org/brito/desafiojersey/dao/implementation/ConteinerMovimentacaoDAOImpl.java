package org.brito.desafiojersey.dao.implementation;

import org.brito.desafiojersey.dao.ConteinerMovimentacaoDAO;
import org.brito.desafiojersey.dao.utils.SqlLoaderUtils;
import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.domain.ConteinerMovimentacao;
import org.brito.desafiojersey.exceptions.MovimentacaoException;
import org.brito.desafiojersey.utils.MessageUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConteinerMovimentacaoDAOImpl implements ConteinerMovimentacaoDAO {

    @Override
    public void insereConteineresMovimentacoes(ConteinerMovimentacao conteinerMovimentacao) throws MovimentacaoException {
        String sql = SqlLoaderUtils.getSql("conteiner.movimentacao.insere");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            preencherStatement(stmt, conteinerMovimentacao);
            executarQueryConteinerMovimentacao(stmt);
        } catch (SQLException e) {
            throw new MovimentacaoException(
                    MessageUtils.buscaValidacao("database.erro.buscar.id", e.getMessage()));
        }
    }

    private void preencherStatement(PreparedStatement stmt, ConteinerMovimentacao conteinerMovimentacao) throws SQLException {
        stmt.setLong(1, conteinerMovimentacao.getConteinerId());
        stmt.setLong(2, conteinerMovimentacao.getMovimentacaoId());
    }

    private void executarQueryConteinerMovimentacao(PreparedStatement stmt) throws SQLException, MovimentacaoException {
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas == 0) {
            throw new MovimentacaoException(
                    MessageUtils.buscaValidacao("movimentacao.conteiner.erro"));
        }
    }
}
