package org.brito.desafiojersey.dao.db;

import org.brito.desafiojersey.config.Configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Configurations.getDbUrl() + Configurations.getDbName(),
                Configurations.getDbUser(),
                Configurations.getDbPassword());
    }

}
