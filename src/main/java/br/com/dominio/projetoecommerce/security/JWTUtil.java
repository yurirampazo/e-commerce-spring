package br.com.dominio.projetoecommerce.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JWTUtil {

  @Value("${jwt.expiration}")
  private Long expiration;

  public String generateAccessToken(HttpServletRequest request,
                              User user) {
    final Key secret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    log.info("Generating Access Token");
    return Jwts.builder()
          .setSubject(user.getUsername())
          .setIssuedAt(new Date(System.currentTimeMillis()))
          .setExpiration(new Date(System.currentTimeMillis() + expiration))
          .setIssuer(request.getRequestURL().toString())
          .signWith(secret, SignatureAlgorithm.HS512)
          .compact();
  }

  public boolean isTokenValid(String token) {
    log.info("Validation of token!");
    Claims claims = getClaims(token);
    if (claims != null) {
      String username = claims.getSubject();
      Date expirationDate = claims.getExpiration();
      Date now = new Date(System.currentTimeMillis());
      return username != null && expirationDate != null && now.before(expirationDate);
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

  private Claims getClaims(String token) {
    try {
      final Key secret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
      log.info("Getting user claims");
      return Jwts.parserBuilder()
            .setSigningKey(secret)
            .build().
            parseClaimsJws(token).getBody();
    }
    catch (Exception e) {
      return null;
    }
  }
}