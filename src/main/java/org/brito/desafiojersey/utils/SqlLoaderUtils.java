package org.brito.desafiojersey.utils;

import org.brito.desafiojersey.exceptions.CarregamentoExternoException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlLoaderUtils {

    private static Properties sqlProps = new Properties();

    static {
        try (InputStream input = SqlLoaderUtils.class.getClassLoader().getResourceAsStream("sql.properties")) {
            if (input == null) {
                throw new CarregamentoExternoException(
                        MessageUtils.buscaValidacao("carregamento.externo.arquivo.nao.encontrado", "sql.properties"));
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
