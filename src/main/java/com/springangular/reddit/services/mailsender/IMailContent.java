package com.springangular.reddit.services.mailsender;

public interface IMailContent {
  String ACTIVATION_ATTEMPT_MAIL_SUBJECT = "Reddit Account Activation";
  String ACTIVATION_SUCCESS_MAIL_SUBJECT = "Yayyy! Your Account was Activated";
  String MAIL_SENDER = "no-reply@reddit.com";

  String ACTIVATION_ATTEMPT_MAIL_BODY =
          "Thank you for signing-up with us!\n"
                  + "You still have a final step to make before you can proceed with your account\n"
                  + "Please click on the link below to activate your account\n"
                  + "http://localhost:8080/api/auth/accountactivation/";
  String ACTIVATION_SUCCESS_MAIL_BODY =
          "Thank you for signing-up with us!\n" + "Your account has been activated successfully!";
}
