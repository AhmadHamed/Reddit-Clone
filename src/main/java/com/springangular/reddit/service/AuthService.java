package com.springangular.reddit.service;

import com.springangular.reddit.dto.RegisterRequest;
import com.springangular.reddit.models.User;
import com.springangular.reddit.repositories.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
public class AuthService {

  private final PasswordEncoder passwordEncoder;
  private final UserRep userRep;

  @Transactional
  public void signup(RegisterRequest registerRequest) {
    User user = new User();
    user.setUserName(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    user.setCreationDate(Instant.now());
    user.setEnabled(false);
    userRep.save(user);
  }
}
