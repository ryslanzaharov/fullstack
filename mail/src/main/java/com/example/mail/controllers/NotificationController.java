package com.example.mail.controllers;

import com.example.mail.domain.Notification;
import com.example.mail.service.ReadMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotificationController {

    private final JavaMailSender sender;
    private final ReadMail reader;

    @Autowired
    public NotificationController(final JavaMailSender sender,
                                  @Value("${spring.mail.pop3.host}") String host,
                                  @Value("${spring.mail.username}") String username,
                                  @Value("${spring.mail.password}") String pwd) {
        this.sender = sender;
        this.reader = new ReadMail(host, username, pwd);
    }

    @GetMapping("/inbox")
    public List<Notification> index() {
        return reader.receive();
    }

    @GetMapping("/send")
    public String showSendPage() {
        System.out.println("read");
        return "send";
    }


    @PostMapping(value = "/send", consumes = "application/json")
    public ResponseEntity send(@RequestBody Notification msg) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(msg.getTo());
        message.setSubject(msg.getSubject());
        message.setText(msg.getBody());
        this.sender.send(message);
        return new ResponseEntity(HttpStatus.OK);
    }
}
