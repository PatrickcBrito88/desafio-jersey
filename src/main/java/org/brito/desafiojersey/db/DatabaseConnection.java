package org.brito.desafiojersey.db;

import org.brito.desafiojersey.config.VariaveisAmbienteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitária para criação de conexões com o banco de dados.
 * Esta classe utiliza o DriverManager do JDBC para estabelecer conexões usando credenciais e URLs
 * armazenadas nas variáveis de ambiente configuradas em {@link VariaveisAmbienteConfig}.
 */
public class DatabaseConnection {

    /**
     * Estabelece e retorna uma conexão com o banco de dados.
     *
     * Este método monta a URL do banco de dados, o nome de usuário e a senha, recuperando-os das variáveis
     * de ambiente, e utiliza {@link DriverManager} para criar e retornar uma nova conexão com o banco de dados.
     *
     * @return Um novo objeto {@link Connection} para interação com o banco de dados.
     * @throws SQLException se ocorrer um erro ao estabelecer a conexão.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                VariaveisAmbienteConfig.getDbUrl() + VariaveisAmbienteConfig.getDbName(),
                VariaveisAmbienteConfig.getDbUser(),
                VariaveisAmbienteConfig.getDbPassword());
    }
}
