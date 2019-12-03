package com.auth.authorization.controller;

import com.auth.authorization.domain.Message;
import com.auth.authorization.domain.User;
import com.auth.authorization.repository.MessageRepository;
import com.auth.authorization.repository.RoomRepository;
import com.auth.authorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public RoomController(final UserRepository userRepository, final RoomRepository roomRepository,
                          final MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/index")
    public String findAll(ModelMap model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/chat/{id}", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Message>> getMessages(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(
                messageRepository.getMessageByRoomId(id).orElse(new ArrayList<>()),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/chat/{id}/{message}", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Message>> sendMessage(@PathVariable("id") Integer id,
                                                     @PathVariable("message") String message) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getByUserName(auth.getName()).orElseThrow(() -> new UserNotFoundException());
        messageRepository.save(new Message(message, id, user));
        return new ResponseEntity<>(
                messageRepository.getMessageByRoomId(id).orElse(null),
                HttpStatus.OK
        );
    }
}
