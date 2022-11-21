package com.urieletyoh.kitty.controller;

import com.urieletyoh.kitty.entity.User;
import com.urieletyoh.kitty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/api/users")
    public List<User> getUsers() {
        List<User> users  = repository.findAll();
        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUser(@PathVariable Long userId) {
        User user = new User();
        if(userId != null) {
            Optional<User> optionalUser = repository.findById(userId);
            if(optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }
        return user;
    }
    @PostMapping("/api/users")
    public void addUser(@RequestBody User user) {
        repository.save(user);
    }
}
