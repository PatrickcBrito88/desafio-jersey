package org.brito.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.brito.config.Configurations;
import org.brito.infrastructure.db.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;


public class AuthenticationService {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 3600000; // 1 hora

    public static String authenticate(String login, String password) {
        String storedHash = retrieveUserPasswordHash(login);

        if (storedHash != null && BCrypt.checkpw(password, storedHash)) {
            return generateJwtToken(login);
        } else {
            return null;
        }
    }

    private static String retrieveUserPasswordHash(String login) {
        String sql = "SELECT password FROM usuarios WHERE login = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateJwtToken(String login) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + EXPIRATION_TIME)) // Token válido por 1 hora
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

}
