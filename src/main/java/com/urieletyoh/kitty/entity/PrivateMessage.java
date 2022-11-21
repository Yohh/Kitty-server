package com.urieletyoh.kitty.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne()
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private String content;

    public PrivateMessage(){};

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
