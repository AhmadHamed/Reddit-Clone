package com.springangular.reddit.security;

import com.springangular.reddit.exceptions.SpringRedditException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyStore;
import java.security.PublicKey;

@Service
@AllArgsConstructor
public class JwtProvider {
  private final String JAVA_KEY_STORE = "springblog";
  private final String SECRET = "secret";
  private final KeyStore keyStore;

  public String generateJwt(Authentication authentication) {
    User principal = (User) authentication.getPrincipal();
    return Jwts.builder().setSubject(principal.getUsername()).signWith(getPrivateKey()).compact();
  }

  private Key getPrivateKey() {
    try {
      return keyStore.getKey(JAVA_KEY_STORE, SECRET.toCharArray());
    } catch (Exception e) {
      throw new SpringRedditException(
              "An error happened while loading the public key from " + JAVA_KEY_STORE);
    }
  }

  public boolean validateJwt(String jwt) {
    Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
    return true;
  }

  public String getUserNameFromJwt(String token) {
    Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

  private PublicKey getPublicKey() {
    try {
      return keyStore.getCertificate(JAVA_KEY_STORE).getPublicKey();
    } catch (Exception e) {
      throw new SpringRedditException(
              "An error happened while loading the public key from " + JAVA_KEY_STORE);
    }
  }
}
