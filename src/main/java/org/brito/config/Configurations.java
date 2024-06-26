package org.brito.config;

import java.util.Map;

public class Configurations {

    private static Map<String, String> dbConfig;
    private static Map<String, String> jwtKey;
    private static Map<String, String> adminData;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASS");
        String dbName = System.getenv("DB_NAME");
        String jwtKey = System.getenv("JWT_KEY");
        String loginAdmin = System.getenv("USER_ADMIN_LOGIN");
        String passwordAdmin = System.getenv("USER_ADMIN_PASSWORD");

        if (dbUrl == null || dbUser == null || dbPassword == null || dbName == null || jwtKey == null || loginAdmin == null || passwordAdmin == null) {
            throw new IllegalStateException("Uma ou mais variáveis de ambiente não foram definidas.");
        }

        System.setProperty("dbUrl", dbUrl);
        System.setProperty("dbUser", dbUser);
        System.setProperty("dbPassword", dbPassword);
        System.setProperty("dbName", dbName);
        System.setProperty("jwtKey", jwtKey);
        System.setProperty("loginAdmin", loginAdmin);
        System.setProperty("passwordAdmin", passwordAdmin);
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

    public static String getLoginAdmin() {
        return System.getProperty("loginAdmin");
    }

    public static String getPasswordAdmin() {
        return System.getProperty("passwordAdmin");
    }

}
