package org.brito.desafiojersey.dao.utils;

import org.brito.desafiojersey.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtils {

    public static List<Map<String, Object>> listarPaginado(String tabela,int paginaAtual, int tamanhoPagina) throws SQLException {
        String sql = String.format(SqlLoaderUtils.getSql("busca.todos"), tabela);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tamanhoPagina);
            stmt.setInt(2, paginaAtual * tamanhoPagina);

            try (ResultSet rs = stmt.executeQuery()) {
                List<Map<String, Object>> resultados = new ArrayList<>();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = rs.getObject(i);
                        row.put(columnName, columnValue);
                    }
                    resultados.add(row);
                }

                return resultados;
            }
        }
    }
}
