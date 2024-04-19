package com.learning.microservices.setup.producer.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyMessage {

  private Long userId;

  private String message;

  private Long createdAt;

}
