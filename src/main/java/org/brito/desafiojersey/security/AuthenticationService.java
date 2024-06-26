package org.brito.desafiojersey.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.brito.desafiojersey.config.Configurations;
import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.exceptions.AutenticacaoException;
import org.brito.desafiojersey.utils.MessageUtils;
import org.brito.desafiojersey.utils.SqlLoaderUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Objects;



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
        String sql = SqlLoaderUtils.getSql("usuario.busca.password.por.login");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        } catch (Exception e) {
            throw new AutenticacaoException(
                    MessageUtils.buscaValidacao("auth.validar.password", e.getMessage()));
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
