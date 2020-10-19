package com.springangular.reddit.service;

import com.springangular.reddit.exceptions.RedditMailException;
import com.springangular.reddit.systemobjects.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;
    private Logger logger;

    public void initiateMailActivationProcess(NotificationEmail notificationEmail)
            throws RedditMailException {
        MimeMessagePreparator messagePreparator =
                (mimeMessage) -> {
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                    mimeMessageHelper.setFrom(notificationEmail.getSender());
                    mimeMessageHelper.setTo(notificationEmail.getRecipient());
                    mimeMessageHelper.setSubject(notificationEmail.getSubject());
                    mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
                };
        try {
            sendMail(messagePreparator);
        } catch (MailException e) {
            throw new RedditMailException(
                    "An error occurred during sending the activation mail to "
                            + notificationEmail.getRecipient());
        }
    }

    private void sendMail(MimeMessagePreparator messagePreparator) {
        javaMailSender.send(messagePreparator);

        logger = Logger.getLogger(MailService.class.getName());
        logger.info("Activation mail was sent");
        logger.info("Kindly check your inbox!");
    }
}
