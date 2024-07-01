package org.brito.desafiojersey.dao.utils;

import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.exceptions.DaoException;
import org.brito.desafiojersey.utils.MessageUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    public static <T> List<T> listarPaginado(Class<T> clazz, String tabela,
                                             int paginaAtual, int tamanhoPagina) throws SQLException {
        String sql = String.format(SqlLoaderUtils.getSql("busca.todos"), tabela);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tamanhoPagina);
            stmt.setInt(2, paginaAtual * tamanhoPagina);

            try (ResultSet rs = stmt.executeQuery()) {
                List<T> resultados = new ArrayList<>();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    T instance = clazz.getDeclaredConstructor().newInstance();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = rs.getObject(i);
                        setField(instance, columnName, columnValue);
                    }
                    resultados.add(instance);
                }

                return resultados;
            } catch (ReflectiveOperationException e) {
                throw new DaoException(MessageUtils.buscaValidacao("database.utils.instanciacao.classe",
                        clazz.getName()), e);
            }
        }
    }

    private static <T> void setField(T instance, String fieldName, Object value) throws ReflectiveOperationException {
        Field field = null;
        try {
            field = instance.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            for (Field declaredField : instance.getClass().getDeclaredFields()) {
                if (declaredField.getName().equalsIgnoreCase(fieldName)) {
                    field = declaredField;
                    break;
                }
            }
        }

        if (field != null) {
            field.setAccessible(true);
            field.set(instance, value);
        }
    }
}
