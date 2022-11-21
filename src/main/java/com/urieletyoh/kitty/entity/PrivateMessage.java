package com.urieletyoh.kitty.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

//    @OneToMany(mappedBy = "privateMessage")
//    private List<User> users;


    public PrivateMessage(){};

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

}
