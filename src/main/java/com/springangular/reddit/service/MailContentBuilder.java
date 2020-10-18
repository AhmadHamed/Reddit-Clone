package com.springangular.reddit.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilder {

  private final TemplateEngine templateEngine;
  private final String MAIL_CONTENT_MESSAGE = "message";
  private final String MAIL_TEMPLATE = "mailTemplate";

  public String build(String message) {
    Context context = new Context();
    context.setVariable(MAIL_CONTENT_MESSAGE, message);
    return templateEngine.process(MAIL_TEMPLATE, context);
  }
}
