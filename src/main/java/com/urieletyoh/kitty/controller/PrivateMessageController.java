package com.urieletyoh.kitty.controller;

import com.urieletyoh.kitty.entity.PrivateMessage;
import com.urieletyoh.kitty.repository.PrivateMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PrivateMessageController {

    @Autowired
    private PrivateMessageRepository repository;

    @GetMapping("/api/privatemessages")
    public List<PrivateMessage> getPrivateMessages() {
        List<PrivateMessage> privateMessages = repository.findAll();
        return privateMessages;
    }

}
