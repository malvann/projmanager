package com.my.projmanager.mailing;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@EnableAspectJAutoProxy(proxyTargetClass = true)
@AllArgsConstructor
public class MailSenderService {
    private final JavaMailSender mailSender;


    public void send(String mail, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;

        messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setTo(mail);
        messageHelper.setSubject(subject);
        messageHelper.setText(content);

        mailSender.send(message);
    }
}
