package com.urieletyoh.kitty.controller;

import com.urieletyoh.kitty.entity.PrivateMessage;
import com.urieletyoh.kitty.entity.User;
import com.urieletyoh.kitty.repository.PrivateMessageRepository;
import com.urieletyoh.kitty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PrivateMessageController {

    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/privatemessages")
    public List<PrivateMessage> getPrivateMessages() {
        List<PrivateMessage> privateMessages = privateMessageRepository.findAll();
        return privateMessages;
    }

    @PostMapping("/api/privatemessages/{senderId}/{receiverId}")
    public void addPrivateMessage(@RequestBody PrivateMessage message, @PathVariable Long senderId, @PathVariable Long receiverId) {
        User sender = userRepository.findById(senderId).get();
        User receiver = userRepository.findById(receiverId).get();
        PrivateMessage result = new PrivateMessage();
        result.setContent(message.getContent());
        result.setSender(sender);
        result.setReceiver(receiver);
        privateMessageRepository.save(result);
    }

}
