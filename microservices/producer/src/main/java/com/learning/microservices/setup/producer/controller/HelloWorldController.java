package com.learning.microservices.setup.producer.controller;

import com.learning.microservices.avro.MyAvroMessage;
import com.learning.microservices.setup.producer.config.beans.RandomGeneratorConfiguration;
import com.learning.microservices.setup.producer.domain.MyMessage;
import com.learning.microservices.setup.producer.infrastructure.RandomGenerator;
import java.time.Clock;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloWorldController {

  @Value("${micro-producer.property}")
  private String configServer;

  private final StreamBridge streamBridge;

  private final RandomGeneratorConfiguration randomGeneratorConfiguration;

  @GetMapping("/hello-world")
  public ResponseEntity<String> helloWorld() {
    log.info("Hello world works!");
    return ResponseEntity.ok(String.format("Hello %s world!", configServer));
  }

  @GetMapping("/hello-kafka")
  public ResponseEntity<String> helloKafka() {
    log.info("Hello kafka works!");
    streamBridge.setAsync(true);
    streamBridge.send("my-message-producer-out-0", createMessage());
    return ResponseEntity.accepted().build();
  }

  @GetMapping("/secured/hello-world")
  public ResponseEntity<String> securedHelloWorld() {
    return ResponseEntity.ok("this hello world is secured");
  }

  private Message<MyAvroMessage> createMessage() {

    MyMessage myMessage = MyMessage.builder().userId(RandomGenerator.userId())
        .message(RandomGenerator.sentence(randomGeneratorConfiguration.getKeys()))
        .createdAt(Instant.now(Clock.systemUTC()).toEpochMilli())
        .build();
    MyAvroMessage content = MyAvroMessage.newBuilder().setUserId(myMessage.getUserId())
        .setMessage(myMessage.getMessage()).setCreatedAt(myMessage.getCreatedAt()).build();

    return MessageBuilder.withPayload(content)
        .setHeader(KafkaHeaders.KEY, String.valueOf(content.getUserId()))
        .build();
  }

}
