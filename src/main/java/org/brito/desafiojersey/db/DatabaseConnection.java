package org.brito.desafiojersey.db;

import org.brito.desafiojersey.config.VariaveisAmbienteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                VariaveisAmbienteConfig.getDbUrl() + VariaveisAmbienteConfig.getDbName(),
                VariaveisAmbienteConfig.getDbUser(),
                VariaveisAmbienteConfig.getDbPassword());
    }

}
