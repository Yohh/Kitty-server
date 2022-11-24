 package com.urieletyoh.kitty.config;


import com.urieletyoh.kitty.service.ChatLauncher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServerCommandLineRunner implements CommandLineRunner {

   // private final ChatLauncher server;
    @Override
    public void run(String... args) throws Exception {
       // server.start();
    }

}
