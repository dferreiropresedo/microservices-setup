package com.learning.microservices.dep.observability;

import com.learning.microservices.internal.commons.IntYamlPropertiesLoader;

public class DepObservabilityYamlLoader extends IntYamlPropertiesLoader {

  private static final String CONFIGURATION_FILE = "observability";

  public DepObservabilityYamlLoader() {
    super(CONFIGURATION_FILE);
  }
}
