package com.spring.market.configs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    // Получение всей информации о клиенте
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Получение отдельныех полей
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

//    private Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public boolean validateToken(String token) {
//        return !isTokenExpired(token);
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String username = getUsernameFromToken(token);
//        return Objects.equals(username, userDetails.getUsername()) && !isTokenExpired(token);
//    }

    // собирает claims - права пользователя на этапе аутентификации
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // Кладем роли в claims в виде листа строк
        claims.put("roles", rolesList);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // Сброка токена на основе полученной информации о клиенте от сервера
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + 60 * 60 * 1000); // todo
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Метод получает полезную информацию о клиенте из токена
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public List<String> getRoles(String token) {
        // Достаются Claims (полезная информация о клиенте)
        // отдаем функцию объясняющую как получить из Claims - список строк (ролей)
        // у claims запросили поле roles являющееся листом строк
        return getClaimFromToken(token, (Function<Claims, List<String>>) claims -> claims.get("roles", List.class));
    }
//
//    private boolean isTokenExpired(String token) {
//        Date date = getExpirationDateFromToken(token);
//        return date != null && date.before(new Date());
//    }
}
