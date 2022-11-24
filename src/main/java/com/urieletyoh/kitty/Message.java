package com.urieletyoh.kitty;

import lombok.Data;

import java.awt.*;

@Data
public class Message {
    private String message;
    private String author;
    public Message() {}

    public Message(String message, String author) {
        this.message = message;
        this.author = author;
    }
}

