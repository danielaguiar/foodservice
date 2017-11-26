package com.gestaosimples.arquitetura.security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.gestaosimples.arquitetura.util.ObjetoUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserSS user) {

        return Jwts.builder().setSubject(user.getUsername()) //
            .setExpiration(new Date(System.currentTimeMillis() + expiration)) //
            .signWith(SignatureAlgorithm.HS512, secret.getBytes()) //
            //.setClaims(claims) //
            .claim("idEmpresa", user.getIdEmpresa()) //
            .claim("idUsuario", user.getId()) //
            .compact();
    }

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    public Long getEmpresa(String token) {
        Claims claims = getClaims(token);
        if (claims != null && !ObjetoUtil.isVazio(claims.get("idEmpresa"))) {
            Object id = claims.get("idEmpresa");
            return (Long) id;
        }
        return null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
