package com.learning.microservices.setup.consumer.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyMessage {

  private Long userId;

  private String message;

  private Long createdAt;

}
