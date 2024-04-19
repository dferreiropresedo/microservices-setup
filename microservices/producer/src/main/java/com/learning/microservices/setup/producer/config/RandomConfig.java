package com.learning.microservices.setup.producer.config;

import com.learning.microservices.setup.producer.config.beans.RandomGeneratorConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomConfig {

  @Bean
  @ConfigurationProperties("micro-producer.random")
  public RandomGeneratorConfiguration randomGeneratorConfiguration() {
    return new RandomGeneratorConfiguration();
  }

}
