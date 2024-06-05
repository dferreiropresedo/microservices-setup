package com.learning.microservices.setup.consumer.controller;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SecurityCheckController {


  @GetMapping("/check-public")
  public ResponseEntity<String> checkPublic(Principal principal) {
    log.info("Check public works");
    return ResponseEntity.ok("Check public works");
  }

  @GetMapping("/check-private")
  @PreAuthorize("hasRole('ROLE_UMA_AUTHORIZATION')")
  public ResponseEntity<String> checkPrivate(Principal principal) {
    log.info("Check private works");
    return ResponseEntity.ok("Check private works");
  }

}
