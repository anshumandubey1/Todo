package com.anshuman.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  //For testing, disable csrf
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
//        .csrf().disable()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2Login();
    return http.build();
  }

}
