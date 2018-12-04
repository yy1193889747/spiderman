package com.cy.spiderman.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author congyang.guo
 */
@Component
public class JavaMail {

    @Autowired
    private JavaMailSender mailSender;


    public void sendMusic(String msg) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("704739362@qq.com");
        message.setTo("704739362@qq.com");
        message.setSubject("你有一条新消息");
        message.setText("msg");

        mailSender.send(message);

    }
}
