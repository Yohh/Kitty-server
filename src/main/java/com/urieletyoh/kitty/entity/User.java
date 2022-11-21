package com.urieletyoh.kitty.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "sender")
    private List<PrivateMessage> senderPrivateMessages;
    @OneToMany(mappedBy = "receiver")
    private List<PrivateMessage> receiverPrivateMessages;

    public User(){}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PrivateMessage> getSenderPrivateMessages() {
        return this.senderPrivateMessages;
    }

    public void setSenderPrivateMessages(List<PrivateMessage> senderPrivateMessages) {
        this.senderPrivateMessages = senderPrivateMessages;
    }

    public List<PrivateMessage> getReceiverPrivateMessages() {
        return receiverPrivateMessages;
    }

    public void setReceiverPrivateMessages(List<PrivateMessage> receiverPrivateMessages) {
        this.receiverPrivateMessages = receiverPrivateMessages;
    }
}