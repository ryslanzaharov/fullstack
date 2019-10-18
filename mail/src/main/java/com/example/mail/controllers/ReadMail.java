package com.example.mail.controllers;

import com.example.mail.domain.Notification;
import com.example.mail.domain.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ReadMail {

    @RequestMapping(value = "/inbox", method = RequestMethod.GET)
    public String getPageInbox() {
        return "inbox";
    }

    @RequestMapping(value = "/inbox", method = RequestMethod.POST)
    public void receiveMessages(@ModelAttribute UserData user, HttpServletResponse response) {
        try {
            List<Notification> messages = new ArrayList<>();
            Properties properties = new Properties();
            properties.put("mail.pop3.host", user.getHost());
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("pop3s");
            store.connect(user.getHost(), user.getUserName(), user.getPassword());
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            for (var msg : inbox.getMessages()) {
                response.getWriter().print(String.format("%s %s %s", msg.getFrom()[0].toString(), msg.getSubject(), msg.getContent().toString()));
            }
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
