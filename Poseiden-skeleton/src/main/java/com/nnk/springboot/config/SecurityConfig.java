package com.nnk.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // désactive CSRF
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // autorise tout
                .formLogin(AbstractHttpConfigurer::disable) // désactive le formulaire login
                .httpBasic(AbstractHttpConfigurer::disable); // désactive l'auth basic

        return http.build();
    }
}
