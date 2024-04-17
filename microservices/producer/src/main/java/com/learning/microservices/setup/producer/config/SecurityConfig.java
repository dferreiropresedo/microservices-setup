package com.learning.microservices.setup.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain enableOauth(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth ->
            auth.requestMatchers(new AntPathRequestMatcher("/actuator/**"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/hello-world"))
                .permitAll()
                .anyRequest()
                .authenticated()
        );
    httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
    return httpSecurity.build();
  }

}
