package com.springangular.reddit.service;

public interface IMailContent {
  String MAIL_SUBJECT = "Reddit Mail Activation";
  String MAIL_SENDER = "ahmad_hamed95@yahoo.com";
  String MAIL_BODY =
          "Thank you for signing-up with us!\n"
                  + "You still have a final step to make before you can proceed with your account\n"
                  + "Please click on the link below to activate your account\n"
                  + "http://localhost:8080/api/auth/accountactivation/";
}
