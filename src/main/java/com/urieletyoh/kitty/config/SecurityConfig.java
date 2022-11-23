package com.urieletyoh.kitty.config;

import com.sun.istack.NotNull;
import com.urieletyoh.kitty.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(@NotNull HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/", "/api/login", "/api/register", "/api/logout").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("http://localhost:4200/login").permitAll()
                .defaultSuccessUrl("http://localhost:4200/chat")
                .failureUrl("http://localhost:4200/login")
                .and()
                .logout().permitAll()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, MyUserDetailsService myUserDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }
}
