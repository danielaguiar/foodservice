package com.gestaosimples.arquitetura.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.gestaosimples.arquitetura.util.ObjetoUtil;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserSS user) {

        return Jwts.builder().setSubject(user.getUsername()) //
            .setExpiration(new Date(System.currentTimeMillis() + expiration)) //
            .signWith(SignatureAlgorithm.HS256, secret.getBytes()) //
            .claim("idEmpresa", user.getIdEmpresa()) //
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
            if (id instanceof Integer) {
                Integer idEmpInteger = (Integer) id;
                return idEmpInteger.longValue();
            }
            return (Long) id;
        }
        return null;
    }

    public List<SimpleGrantedAuthority> getRoles(String token) {
        Claims claims = getClaims(token);
        if (claims != null && !ObjetoUtil.isVazio(claims.get("roles"))) {
            List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
            String id = (String) claims.get("roles");
            String[] role = id.replace("[", "").replace("]", "").split(",");
            for (String r : role) {
                roles.add(new SimpleGrantedAuthority(r));
            }
            return roles;
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
