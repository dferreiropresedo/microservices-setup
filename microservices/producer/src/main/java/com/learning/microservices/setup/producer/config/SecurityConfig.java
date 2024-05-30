package com.learning.microservices.setup.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  @Order(1)
  public SecurityFilterChain helloWorldPublic(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .securityMatcher("/hello-world")
        .authorizeHttpRequests(auth ->
            auth.anyRequest().permitAll()
        )
    ;
    return httpSecurity.build();
  }

  @Bean
  @Order(2)
  public SecurityFilterChain helloKafkaPublic(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .securityMatcher("/hello-kafka")
        .authorizeHttpRequests(auth ->
            auth.anyRequest().permitAll()
        )
    ;
    return httpSecurity.build();
  }
}
