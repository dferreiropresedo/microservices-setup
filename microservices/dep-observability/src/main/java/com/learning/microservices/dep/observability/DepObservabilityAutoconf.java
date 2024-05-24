package com.learning.microservices.dep.observability;

import io.micrometer.tracing.exporter.FinishedSpan;
import io.micrometer.tracing.exporter.SpanExportingPredicate;
import java.util.Optional;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(name = "tools.observability.enabled", havingValue = "true")
public class DepObservabilityAutoconf {

  public static final String URI_FIELD = "uri";

  @Bean
  public SpanExportingPredicate noActuator() {
    return finishedSpan -> !uriContainsValue(finishedSpan, "/actuator");
  }

  private boolean uriContainsValue(FinishedSpan span, String uriContent) {
    return Optional.ofNullable(span.getTags().get(URI_FIELD)).orElse("").contains(uriContent);
  }
}
