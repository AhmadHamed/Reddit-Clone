package com.springangular.reddit.security;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@AllArgsConstructor
public class JwtProvider {

  public String generateToke(Authentication authentication) {
    User principal = (User) authentication.getPrincipal();
    return Jwts.builder().setSubject(principal.getUsername()).signWith(getPrivateKey()).compact();
  }

  private Key getPrivateKey() {
    return null;
  }
}
