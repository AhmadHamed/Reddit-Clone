package com.springangular.reddit.service;

import com.springangular.reddit.dto.AuthenticationResponse;
import com.springangular.reddit.dto.LoginRequest;
import com.springangular.reddit.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;

  public AuthenticationResponse login(LoginRequest loginRequest) {
    Authentication authentication =
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtProvider.generateToke(authentication);
    return new AuthenticationResponse(loginRequest.getUsername(), token);
  }
}
