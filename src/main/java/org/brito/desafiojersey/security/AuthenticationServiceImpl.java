package org.brito.desafiojersey.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.brito.desafiojersey.config.VariaveisAmbienteConfig;
import org.brito.desafiojersey.dao.utils.SqlLoaderUtils;
import org.brito.desafiojersey.db.DatabaseConnection;
import org.brito.desafiojersey.exceptions.AutenticacaoException;
import org.brito.desafiojersey.utils.MessageUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Objects;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Key key = Keys.hmacShaKeyFor(gerarChaveSecreta());
    private static final long EXPIRACAO = 3600000;

    public static Key getKey() {
        return key;
    }

    public String buscarToken(String login, String password) {
        String storedHash = buscarPasswordPeloLogin(login);
        if (Objects.nonNull(storedHash) && BCrypt.checkpw(password, storedHash)) {
            return generateJwtToken(login);
        } else {
            return null;
        }
    }

    private String buscarPasswordPeloLogin(String login) {
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

    private String generateJwtToken(String login) {
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        Date expiration = new Date(millis + EXPIRACAO);
        return Jwts.builder()
                .claim("sub", login)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    private static byte[] gerarChaveSecreta() {
        String jwtKey = VariaveisAmbienteConfig.getJwtKey();
        return jwtKey.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }


}
