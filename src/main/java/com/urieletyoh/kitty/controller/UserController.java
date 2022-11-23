package com.urieletyoh.kitty.controller;

import com.urieletyoh.kitty.entity.User;
import com.urieletyoh.kitty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationManager manager;

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users  = repository.findAll();
        return users;
    }

    @GetMapping("/users/{userId}")
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

    @PostMapping("/register")
    public User addUser(@RequestBody User user) {
        String clearTextPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(clearTextPassword));
        User newUser = repository.save(user);
        return newUser;
    }

    @RequestMapping("/login")
    public String login(@RequestBody User user) {
           /* Optional<User> optionalUser = Optional.ofNullable(repository.findByUsername(user.getUsername()));
            if(optionalUser.isPresent()) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                return passwordEncoder.matches(user.getPassword(), optionalUser.get().getPassword());
            }
            return false; */
        System.out.println(manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())));
       Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
       return authentication.getName();
    }
}
