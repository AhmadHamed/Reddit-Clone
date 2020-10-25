package com.springangular.reddit.service;

import com.springangular.reddit.dto.RegisterRequest;
import com.springangular.reddit.models.User;
import com.springangular.reddit.models.VerificationToken;
import com.springangular.reddit.repositories.UserRep;
import com.springangular.reddit.repositories.VerificationTokenRep;
import com.springangular.reddit.systemobjects.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

import static com.springangular.reddit.service.IMailContent.MAIL_BODY;
import static com.springangular.reddit.service.IMailContent.MAIL_SUBJECT;

@Service
@AllArgsConstructor
public class AuthService {

  private final PasswordEncoder passwordEncoder;
  private final UserRep userRep;
  private final VerificationTokenRep verificationTokenRep;
  private final MailService mailService;

  @Transactional
  public void signup(RegisterRequest registerRequest) {
    User user = setUserData(registerRequest);

    String token = generateVerificationToken(user);

    mailService.initiateMailActivationProcess(
            new NotificationEmail(MAIL_SUBJECT, user.getEmail(), MAIL_BODY + token));
  }

  private User setUserData(RegisterRequest registerRequest) {
    User user = new User();
    user.setUserName(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    user.setCreationDate(Instant.now());
    user.setEnabled(false);

    userRep.save(user);

    return user;
  }

  private String generateVerificationToken(User user) {
    String token = UUID.randomUUID().toString();
    VerificationToken verificationToken = new VerificationToken();
    verificationToken.setToken(token);
    verificationToken.setUser(user);
    verificationTokenRep.save(verificationToken);

    return token;
  }
}
