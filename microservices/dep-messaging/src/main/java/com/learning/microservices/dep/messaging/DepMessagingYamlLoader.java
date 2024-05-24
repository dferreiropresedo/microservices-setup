package com.learning.microservices.dep.messaging;

import com.learning.microservices.internal.commons.IntYamlPropertiesLoader;

public class DepMessagingYamlLoader extends IntYamlPropertiesLoader {

  private static final String CONFIGURATION_FILE = "messaging";

  public DepMessagingYamlLoader() {
    super(CONFIGURATION_FILE);
  }
}
