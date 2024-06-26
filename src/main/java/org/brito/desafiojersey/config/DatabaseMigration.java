package org.brito.desafiojersey.config;

import org.flywaydb.core.Flyway;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static org.brito.desafiojersey.constantes.scripts.CRIAR_DATABASE;

public class DatabaseMigration {

    private static final String URL = Configurations.getDbUrl();
    private static final String DB_NAME = Configurations.getDbName();
    private static final String USER = Configurations.getDbUser();
    private static final String PASSWORD = Configurations.getDbPassword();

    private static final String USER_ADMIN = System.getenv("USER_ADMIN_LOGIN");
    private static final String PASSWORD_ADMIN = System.getenv("USER_ADMIN_PASSWORD");



    public static void iniciaBanco() {
        criaBancoSeNaoExiste();

        Flyway.configure()
                .dataSource(URL + DB_NAME, USER, PASSWORD)
                .placeholders(Map.of(
                        "login", USER_ADMIN,
                        "password", buscaPassCriptografado()
                ))
                .load()
                .migrate();

    }

    private static void criaBancoSeNaoExiste() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            ResultSet resultSet = conn.getMetaData().getCatalogs();
            boolean dbExiste = false;
            while (resultSet.next()) {
                String nomeDatabase = resultSet.getString(1);
                if (nomeDatabase.equals(DB_NAME)) {
                    dbExiste = true;
                    break;
                }
            }
            resultSet.close();

            if (!dbExiste) {
                conn.createStatement().execute(CRIAR_DATABASE);
                System.out.println("Banco de dados " + DB_NAME + " criado com sucesso.");
            } else {
                System.out.println("Banco de dados " + DB_NAME + " já existe.");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    private static String buscaPassCriptografado() {
        return BCrypt.hashpw(PASSWORD_ADMIN, BCrypt.gensalt(12));
    }

}
