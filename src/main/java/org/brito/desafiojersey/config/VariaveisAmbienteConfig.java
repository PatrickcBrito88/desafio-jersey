package org.brito.desafiojersey.config;

import org.brito.desafiojersey.utils.MessageUtils;

import java.util.Objects;

public class VariaveisAmbienteConfig {

    static {
        loadConfig();
    }

    private static void loadConfig() {
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASS");
        String dbName = System.getenv("DB_NAME");
        String jwtKey = System.getenv("JWT_KEY");
        String host = System.getenv("HOST");

        if (Objects.isNull(dbUrl)
                || Objects.isNull(dbUser)
                || Objects.isNull(dbPassword)
                || Objects.isNull(dbName)
                || Objects.isNull(jwtKey)
                || Objects.isNull(host)) {
            throw new IllegalStateException(
                    MessageUtils.buscaValidacao("config.variaveis.ambiente.nao.encontradas"));
        }

        System.setProperty("dbUrl", dbUrl);
        System.setProperty("dbUser", dbUser);
        System.setProperty("dbPassword", dbPassword);
        System.setProperty("dbName", dbName);
        System.setProperty("jwtKey", jwtKey);
        System.setProperty("host", host);
    }

    public static String getDbUrl() {
        return System.getProperty("dbUrl");
    }

    public static String getDbUser() {
        return System.getProperty("dbUser");
    }

    public static String getDbPassword() {
        return System.getProperty("dbPassword");
    }

    public static String getDbName() {
        return System.getProperty("dbName");
    }

    public static String getJwtKey() {
        return System.getProperty("jwtKey");
    }

    public static String getHost() {
        return System.getProperty("host");
    }

}
