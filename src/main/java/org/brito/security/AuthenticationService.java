package org.brito.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.brito.config.Configurations;
import org.brito.infrastructure.db.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Objects;

import static org.brito.constantes.scripts.BUSCAR_PASSWORD_PELO_LOGIN;


public class AuthenticationService {

    private static final Key key = Keys.hmacShaKeyFor(gerarChaveSecreta());
    private static final long EXPIRACAO = 3600000;

    public static Key getKey() {
        return key;
    }

    public static String buscarToken(String login, String password) {
        String hashArmazenado = validarPassword(login);

        if (Objects.nonNull(hashArmazenado) && BCrypt.checkpw(password, hashArmazenado)) {
            return gerarJwtToken(login);
        } else {
            return null;
        }
    }

    private static String validarPassword(String login) {
        String sql = BUSCAR_PASSWORD_PELO_LOGIN;
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

    private static String gerarJwtToken(String login) {
        long millis = System.currentTimeMillis();
        Date agora = new Date(millis);
        Date expiracao = new Date(millis + EXPIRACAO);
        return Jwts.builder()
                .claim("sub", login)
                .setIssuedAt(agora)
                .setExpiration(expiracao)
                .signWith(key)
                .compact();
    }

    private static byte[] gerarChaveSecreta() {
        String jwtKey = Configurations.getJwtKey();
        return jwtKey.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

}
