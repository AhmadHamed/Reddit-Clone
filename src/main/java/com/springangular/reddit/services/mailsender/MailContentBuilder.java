package com.springangular.reddit.services.mailsender;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilder {

  private final String MAIL_CONTENT_MESSAGE = "message";
  private final String MAIL_TEMPLATE = "mailTemplate";
  private final TemplateEngine templateEngine;

  public String build(String message) {
    Context context = new Context();
    context.setVariable(MAIL_CONTENT_MESSAGE, message);
    return templateEngine.process(MAIL_TEMPLATE, context);
  }
}
