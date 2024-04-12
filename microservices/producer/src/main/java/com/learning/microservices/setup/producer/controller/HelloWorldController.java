package com.learning.microservices.setup.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloWorldController {

  @Value("${micro-producer.property}")
  private String configServer;

  @GetMapping("/hello-world")
  public ResponseEntity<String> helloWorld() {
    return ResponseEntity.ok(String.format("Hello %s world!", configServer));
  }

  @GetMapping("/secured/hello-world")
  public ResponseEntity<String> securedHelloWorld() {
    return ResponseEntity.ok("this hello world is secured");
  }

}
