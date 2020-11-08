package com.springangular.reddit.services.mailsender;

import com.springangular.reddit.exceptions.RedditMailException;
import com.springangular.reddit.systemobjects.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.springangular.reddit.services.mailsender.IMailContent.MAIL_SENDER;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void initiateMailBuildingProcess(NotificationEmail notificationEmail)
            throws RedditMailException {
        MimeMessagePreparator messagePreparator =
                (mimeMessage) -> {
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                    mimeMessageHelper.setFrom(MAIL_SENDER);
                    mimeMessageHelper.setTo(notificationEmail.getRecipient());
                    mimeMessageHelper.setSubject(notificationEmail.getSubject());
                    mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
                };
        try {
            sendMail(messagePreparator);
        } catch (MailException e) {
            throw new RedditMailException(
                    "An error occurred during sending the e-mail to " + notificationEmail.getRecipient());
        }
    }

    private void sendMail(MimeMessagePreparator messagePreparator) {
        javaMailSender.send(messagePreparator);

        log.info("An E-mail was sent");
        log.info("Kindly check your inbox!");
    }
}
