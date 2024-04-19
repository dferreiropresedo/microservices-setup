package com.learning.microservices.setup.producer.config.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RandomGeneratorConfiguration {

  private String[] keys;

}
