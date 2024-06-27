package org.brito.desafiojersey.dao.utils;

import org.brito.desafiojersey.exceptions.CarregamentoExternoException;
import org.brito.desafiojersey.utils.MessageUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlLoaderUtils {

    private static Properties sqlProps = new Properties();

    static {
        try (InputStream input = SqlLoaderUtils.class.getClassLoader().getResourceAsStream("Sql-querys.properties")) {
            if (input == null) {
                throw new CarregamentoExternoException(
                        MessageUtils.buscaValidacao("carregamento.externo.arquivo.nao.encontrado", "Sql-querys.properties"));
            }
            sqlProps.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getSql(String key) {
        return sqlProps.getProperty(key);
    }
}
