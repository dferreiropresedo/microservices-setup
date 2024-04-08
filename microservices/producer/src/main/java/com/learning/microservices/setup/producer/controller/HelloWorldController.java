package com.learning.microservices.setup.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloWorldController {

  @GetMapping("/hello-world")
  public ResponseEntity<String> helloWorld() {
    return ResponseEntity.ok("Hello world!");
  }

}
