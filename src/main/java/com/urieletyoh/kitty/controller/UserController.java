package com.urieletyoh.kitty.controller;

import com.urieletyoh.kitty.entity.User;
import com.urieletyoh.kitty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"}, allowCredentials = "true")
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
    public User addUser(@RequestBody User user) {
        String clearTextPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(clearTextPassword));
        User newUser = repository.save(user);
        return newUser;
    }

    @Autowired
    public AuthenticationManager manager;
    @RequestMapping("/api/login")
    public Authentication login(@RequestBody User user) {
          /*  Optional<User> optionalUser = Optional.ofNullable(repository.findByUsername(user.getUsername()));
            if(optionalUser.isPresent()) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                return passwordEncoder.matches(user.getPassword(), optionalUser.get().getPassword());
            }
            return false; */

        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    @RequestMapping("/api/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

    @RequestMapping("/api/logout")
    public ResponseEntity logout() {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
