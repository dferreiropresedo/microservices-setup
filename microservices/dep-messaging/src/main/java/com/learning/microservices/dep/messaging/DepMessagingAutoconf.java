package com.learning.microservices.dep.messaging;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@AutoConfiguration
@ConditionalOnProperty(name = "tools.kafka.enabled", havingValue = "true")
public class DepMessagingAutoconf {

}
