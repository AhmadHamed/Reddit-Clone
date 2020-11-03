package com.springangular.reddit.service;

import com.springangular.reddit.dto.RegisterRequest;
import com.springangular.reddit.exceptions.SpringRedditException;
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

import static com.springangular.reddit.service.IMailContent.*;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class RegistrationService {

  private final PasswordEncoder passwordEncoder;
  private final UserRep userRep;
  private final VerificationTokenRep verificationTokenRep;
  private final MailService mailService;

  @Transactional
  public void signup(RegisterRequest registerRequest) {
    User user = setUserData(registerRequest);

    String token = generateVerificationToken(user);

    sendAnEmail(ACTIVATION_ATTEMPT_MAIL_SUBJECT, ACTIVATION_ATTEMPT_MAIL_BODY + token, user);
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

  public void activateAccount(String userToken) {
    VerificationToken verificationToken = verificationTokenRep.findOneByToken(userToken);

    if (isNull(verificationToken)) {
      throw new SpringRedditException("Invalid/Expired Token!");
    } else {
      getUserAndActivateAccount(verificationToken);
    }
  }

  @Transactional
  protected void getUserAndActivateAccount(VerificationToken verificationToken) {
    String userName = verificationToken.getUser().getUserName();
    User user = userRep.findByUserName(userName);
    if (isNull(user)) {
      throw new SpringRedditException("Username not found!");
    } else {
      updateUserState(user);
      verificationTokenRep.delete(verificationToken);
      sendAnEmail(ACTIVATION_SUCCESS_MAIL_SUBJECT, ACTIVATION_SUCCESS_MAIL_BODY, user);
    }
  }

  private void updateUserState(User user) {
    user.setEnabled(true);
    userRep.save(user);
  }

  private void sendAnEmail(String mailSubject, String mailBody, User user) {
    mailService.initiateMailBuildingProcess(
            new NotificationEmail(mailSubject, user.getEmail(), mailBody));
  }
}