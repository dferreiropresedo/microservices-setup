package com.learning.microservices.dep.auth;

import com.learning.microservices.internal.commons.IntYamlPropertiesLoader;

public class DepAuthYamlLoader extends IntYamlPropertiesLoader {

  private static final String CONFIGURATION_FILE = "dep-auth";

  public DepAuthYamlLoader() {
    super(CONFIGURATION_FILE);
  }
}
