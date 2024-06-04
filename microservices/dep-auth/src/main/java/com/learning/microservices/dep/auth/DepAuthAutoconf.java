package com.learning.microservices.dep.auth;

import com.learning.microservices.dep.auth.jwt.CustomClaimConverter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@AutoConfiguration
@ConditionalOnProperty(name = "tools.oauth2.enabled", havingValue = "true")
@EnableWebSecurity
@EnableMethodSecurity
public class DepAuthAutoconf {

  @Bean
  public SecurityFilterChain defaultOauthConf(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth ->
            auth.requestMatchers(new AntPathRequestMatcher("/actuator/**/*"))
                .permitAll()
                .anyRequest()
                .authenticated()
        );
    httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(
        jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(new CustomClaimConverter())
    ));
    return httpSecurity.build();
  }
}