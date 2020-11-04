package com.springangular.reddit.security;

import com.springangular.reddit.exceptions.SpringRedditException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyStore;

@Service
@AllArgsConstructor
public class JwtProvider {
  private final String JAVA_KEY_STORE = "springblog";
  private final String SECRET = "secret";
  private final KeyStore keyStore;

  public String generateToke(Authentication authentication) {
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
}
