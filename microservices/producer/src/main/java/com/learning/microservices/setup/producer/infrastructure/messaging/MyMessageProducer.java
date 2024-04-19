package com.learning.microservices.setup.producer.infrastructure.messaging;

import com.learning.microservices.setup.producer.config.beans.RandomGeneratorConfiguration;
import com.learning.microservices.setup.producer.domain.MyMessage;
import com.learning.microservices.setup.producer.infrastructure.RandomGenerator;
import java.time.Clock;
import java.time.Instant;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("my-message-producer")
@Slf4j
@RequiredArgsConstructor
public class MyMessageProducer implements Supplier<String> {

  private final RandomGeneratorConfiguration randomGeneratorConfiguration;


  @Override
  public String get() {
    MyMessage myMessage = MyMessage.builder().userId(RandomGenerator.userId())
        .message(RandomGenerator.sentence(randomGeneratorConfiguration.getKeys()))
        .createdAt(Instant.now(Clock.systemUTC()).toEpochMilli())
        .build();
    log.info("Sending the message: {}", myMessage);
    return myMessage.toString();
  }
}
