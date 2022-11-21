package com.urieletyoh.kitty.controller;

import com.urieletyoh.kitty.entity.User;
import com.urieletyoh.kitty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    @PostMapping("/api/register")
    public void addUser(@RequestBody User user) {
        String clearTextPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(clearTextPassword));
        repository.save(user);
    }
}
