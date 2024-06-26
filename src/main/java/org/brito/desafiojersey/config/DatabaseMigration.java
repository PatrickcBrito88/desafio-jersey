package org.brito.desafiojersey.config;

import org.brito.desafiojersey.enums.ERole;
import org.brito.desafiojersey.dao.db.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

import static org.brito.desafiojersey.constantes.scripts.*;

public class DatabaseMigration {

    public static void iniciaBanco(){
        criarDatabase();
        criarTabela();
        criarUsuario();
    }

    public static void criarDatabase() {
        try (Connection conn = DatabaseConnection.getConnection(true)) {
            String dbName = Configurations.getDbName();
            ResultSet resultSet = conn.getMetaData().getCatalogs();
            boolean dbExiste = false;
            while (resultSet.next()) {
                String nomeDatabase = resultSet.getString(1);
                if (nomeDatabase.equals(dbName)) {
                    dbExiste = true;
                    break;
                }
            }
            resultSet.close();

            if (!dbExiste) {
                conn.createStatement().execute(CRIAR_DATABASE);
                System.out.println("Banco de dados " + dbName + " criado com sucesso.");
            } else {
                System.out.println("Banco de dados " + dbName + " já existe.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void criarTabela() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = CRIAR_TABELA_USUARIOS;
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public static void criarUsuario() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERIR_USUARIO)) {
            pstmt.setString(1, Configurations.getLoginAdmin());
            pstmt.setString(2, BCrypt.hashpw(Configurations.getPasswordAdmin(), BCrypt.gensalt(12)));
            pstmt.setString(3, ERole.ADMIN.getRole());

            pstmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

}
