package org.brito.desafiojersey.dao.utils;

import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.exceptions.DaoException;
import org.brito.desafiojersey.exceptions.DatabaseException;
import org.brito.desafiojersey.utils.MessageUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtils {

    public static long buscarKeyGerada(PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new DatabaseException(MessageUtils.buscaValidacao("database.erro.buscar.id"));
            }
        }
    }

    public static long buscaQuantidadeTotalItensTabela(String nomeTabela){
        String sql = String.format(SqlLoaderUtils.getSql("busca.quantidade.total"), nomeTabela);
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()){
                return rs.getLong(1);
            } else {
                throw new DaoException(MessageUtils.buscaValidacao("erro.buscar.total"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
