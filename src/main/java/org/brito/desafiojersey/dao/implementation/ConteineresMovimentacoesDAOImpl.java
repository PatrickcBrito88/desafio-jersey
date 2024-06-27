package org.brito.desafiojersey.dao.implementation;

import org.brito.desafiojersey.dao.ConteineresMovimentacoesDAO;
import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.domain.ConteineresMovimentacoes;
import org.brito.desafiojersey.exceptions.MovimentacaoException;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.SqlLoaderUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConteineresMovimentacoesDAOImpl implements ConteineresMovimentacoesDAO {


    @Override
    public void insereConteineresMovimentacoes(ConteineresMovimentacoes conteineresMovimentacoes) {
        String sql = SqlLoaderUtils.getSql("conteiner.movimentacao.insere");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, conteineresMovimentacoes.getConteinerId());
            stmt.setLong(2, conteineresMovimentacoes.getMovimentacaoId());

            int linhasAlteradas = stmt.executeUpdate();

            if (linhasAlteradas == 0) {
                throw new MovimentacaoException(
                        MessageUtils.buscaValidacao("movimentacao.conteiner.erro"));
            }
        } catch (SQLException e) {
            throw new MovimentacaoException(
                    MessageUtils.buscaValidacao("database.erro.buscar.id", e.getMessage()));
        }
    }
}
