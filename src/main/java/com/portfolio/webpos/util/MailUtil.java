package com.portfolio.webpos.util;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class MailUtil {

    private JavaMailSender mailSender;

    @Async
    public void SendMail(String mail, int code){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail);
        mailMessage.setSubject("WEBPOS 인증번호");
        mailMessage.setText(Integer.toString(code));
        mailMessage.setFrom("no-reply@mk.com");
        mailSender.send(mailMessage);
    }
}
