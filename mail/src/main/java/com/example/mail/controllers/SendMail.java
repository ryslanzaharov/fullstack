package com.example.mail.controllers;

import com.example.mail.domain.Notification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Controller
public class SendMail {

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String getPageSend() {
        return "send";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void sendMessage(@ModelAttribute Notification notification) throws Exception {
        Properties props = new Properties();
//        Указание хоста.
        props.put("mail.smtp.host", "smtp.gmail.com");
//        Порт подключения для SSL.
        props.put("mail.smtp.socketFactory.port", "465");
//        Подключение осуществляется по закрытому протоколу SSL.
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        Используем авторизацию.
        props.put("mail.smtp.auth", "true");
//        Порт подключения
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(
                props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("notification.job4j@gmail.com", "nuTKrcW8");
                    }
                }
        );
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(notification.getFrom()));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(notification.getFrom())
        );
        message.setSubject(notification.getSubject());
        message.setText(notification.getBody());
        Transport.send(message);
    }
}