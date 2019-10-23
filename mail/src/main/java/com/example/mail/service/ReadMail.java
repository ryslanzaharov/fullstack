package com.example.mail.service;

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

public class ReadMail {

    private final String host;
    private final String user;
    private final String password;

    public ReadMail(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public List<Notification> receive() {
        final var msgs = new ArrayList<Notification>();
        try {
            Properties properties = new Properties();
            properties.put("mail.pop3.host", this.host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("pop3s");
            store.connect(this.host, this.user, this.password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            for (var msg : inbox.getMessages()) {
                msgs.add(new Notification(null, msg.getFrom()[0].toString(), msg.getSubject(), msg.getContent().toString()));
            }
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgs;
    }


}
