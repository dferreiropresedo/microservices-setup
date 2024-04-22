package com.learning.microservices.setup.consumer.infrastructure.messaging;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("my-message-consumer")
@Slf4j
public class MyMessageConsumer implements Consumer<String> {

  @Override
  public void accept(String s) {

    log.info("Consumed the message: {}", s);

  }
}
