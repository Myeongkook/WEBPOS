package com.portfolio.webpos.util;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
@AllArgsConstructor
public class MailUtil {

    private JavaMailSender mailSender;

    @Async
    public void sendCodeMail(String mail, int code){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail);
        mailMessage.setSubject("WEBPOS 인증번호");
        mailMessage.setText(Integer.toString(code));
        mailMessage.setFrom("no-reply@mk.com");
        mailSender.send(mailMessage);
    }

    @Async
    public void sendResetPwMail(String mail, String hash){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            mimeMessage.setSubject("WEBPOS 암호재설정");
            String htmlStr = "<a href='http://localhost:8080/member/resetpw?key="+ hash +"' target='_blank'>암호재설정</a>";
            mimeMessage.setText(htmlStr,"UTF-8","html");
            mimeMessage.setFrom(new InternetAddress("noreply@webpos.com"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
