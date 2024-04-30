package com.learning.microservices.setup.producer.infrastructure.messaging;

import com.learning.microservices.avro.MyAvroMessage;
import com.learning.microservices.setup.producer.config.beans.RandomGeneratorConfiguration;
import com.learning.microservices.setup.producer.domain.MyMessage;
import com.learning.microservices.setup.producer.infrastructure.RandomGenerator;
import java.time.Clock;
import java.time.Instant;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component("my-message-producer")
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "tools.kafka.enabled", havingValue = "true")
public class MyMessageProducer implements Supplier<Message<MyAvroMessage>> {

  private final RandomGeneratorConfiguration randomGeneratorConfiguration;


  @Override
  public Message<MyAvroMessage> get() {
    MyMessage myMessage = MyMessage.builder().userId(RandomGenerator.userId())
        .message(RandomGenerator.sentence(randomGeneratorConfiguration.getKeys()))
        .createdAt(Instant.now(Clock.systemUTC()).toEpochMilli())
        .build();
    MyAvroMessage content = MyAvroMessage.newBuilder().setUserId(myMessage.getUserId())
        .setMessage(myMessage.getMessage()).setCreatedAt(myMessage.getCreatedAt()).build();

    Message<MyAvroMessage> envelope = MessageBuilder.withPayload(content)
        .setHeader(KafkaHeaders.KEY, String.valueOf(content.getUserId()))
        .build();

    log.info("Sending the envelope: {}", envelope);
    return envelope;
  }
}
