# Observability

This module intends to automatically configure everything needed to add the observability-related properties to the Spring
Boot configuration. Moreover, it can be toggled with the property `tools.observability.enabled` on the main configuration.

## How it is autoconfigured

The properties are loaded thanks to the class `DepObservabilityYamlLoader`, which is loaded through the `META-INF/spring.factories`.

The beans to be loaded are configured on the `DepObservabilityAutoconf` class, which is specified on the 
`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` file. There, the autoconfigured files
are declared.

## What does it do

This module contains the following:

* The connection details to connect both the Zipkin and Loki artifacts. They can be found on the YML files.
* The Loki configuration that specifies both the content and format of the logged information. It can be found on the 
`logback-spring.xml`
* It also adds the Micrometer dependencies to enable the observability metrics configuration.
