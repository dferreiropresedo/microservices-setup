package com.learning.microservices.setup.producer.config;

import io.micrometer.tracing.exporter.FinishedSpan;
import io.micrometer.tracing.exporter.SpanExportingPredicate;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

  public static final String URI_FIELD = "uri";

  @Bean
  public SpanExportingPredicate noActuator() {
    return finishedSpan -> !uriContainsValue(finishedSpan, "/actuator");
  }

  private boolean uriContainsValue(FinishedSpan span, String uriContent) {
    return Optional.ofNullable(span.getTags().get(URI_FIELD)).orElse("").contains(uriContent);
  }

}
