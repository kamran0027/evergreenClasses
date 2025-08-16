package com.evergreenClasses.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/images/**").permitAll()
            .requestMatchers("/public/**").permitAll()
            .requestMatchers("/admin/**").authenticated()
        )
        .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
   

}
