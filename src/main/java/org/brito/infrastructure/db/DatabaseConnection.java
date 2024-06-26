package org.brito.infrastructure.db;

import org.brito.config.Configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        System.out.println(Configurations.getDbUrl());
        System.out.println(Configurations.getDbUser());
        System.out.println(Configurations.getDbPassword());
        System.out.println(Configurations.getDbName());


        return DriverManager.getConnection(
                Configurations.getDbUrl() + Configurations.getDbName(),
                Configurations.getDbUser(),
                Configurations.getDbPassword());
    }

    public static Connection getConnection(boolean bancoCriado) throws SQLException {
        System.out.println(Configurations.getDbUrl());
        System.out.println(Configurations.getDbUser());
        System.out.println(Configurations.getDbPassword());
        System.out.println(Configurations.getDbName());

        System.out.println(bancoCriado);

        return DriverManager.getConnection(
                Configurations.getDbUrl(),
                Configurations.getDbUser(),
                Configurations.getDbPassword());
    }

}
