package com.learning.microservices.dep.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class DepMessagingYamlLoader implements EnvironmentPostProcessor {

  private static final DefaultResourceLoader RESOURCE_LOADER = new DefaultResourceLoader();

  private static final String PATTERN = "classpath:%s.yml";

  private static final String CONFIGURATION_FILE = "messaging";

  public static final String APPLICATION_PROPERTY_SOURCES = "my-application-property-sources";

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment,
      SpringApplication application) {
    String[] activeProfiles = environment.getActiveProfiles();
    MapPropertySource propertySource = getPropertySource(environment);

    addDefaultProperties(propertySource);
    for (String activeProfile : activeProfiles) {
      addProfileProperties(propertySource, activeProfile);
    }

  }

  private MapPropertySource getPropertySource(ConfigurableEnvironment environment) {
    PropertySource<?> value = environment.getPropertySources().get(APPLICATION_PROPERTY_SOURCES);
    if (value != null) {
      return (MapPropertySource) value;
    }
    MapPropertySource mapPropertySource = new MapPropertySource(APPLICATION_PROPERTY_SOURCES,
        new HashMap<>());
    environment.getPropertySources().addLast(mapPropertySource);
    return mapPropertySource;
  }

  private void addDefaultProperties(MapPropertySource propertySource) {
    String configurationFileName = String.format(PATTERN, CONFIGURATION_FILE);
    Map properties = loadProperties(configurationFileName);
    propertySource.getSource().putAll(properties);
  }

  private void addProfileProperties(MapPropertySource propertySource, String profile) {
    String configurationFile = CONFIGURATION_FILE + "-" + profile;
    String configurationFileName = String.format(PATTERN, configurationFile);
    Map properties = loadProperties(configurationFileName);
    propertySource.getSource().putAll(properties);
  }


  private Properties loadProperties(String resourceName) {
    Resource resource = RESOURCE_LOADER.getResource(resourceName);
    YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
    factoryBean.setResources(resource);
    return factoryBean.getObject();
  }
}
