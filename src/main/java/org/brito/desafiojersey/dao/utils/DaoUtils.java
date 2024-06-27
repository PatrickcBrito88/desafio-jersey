package org.brito.desafiojersey.dao.utils;

import org.brito.desafiojersey.exceptions.DatabaseException;
import org.brito.desafiojersey.utils.MessageUtils;

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
}
