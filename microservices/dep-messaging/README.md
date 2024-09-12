# Messaging

This module intends to automatically configure everything needed to add the messaging-related properties to the Spring
Boot configuration. Moreover, it can be toggled with the property `tools.kafka.enabled` on the main configuration.

## How it is autoconfigured

The properties are loaded thanks to the class `DepMessagingYamlLoader`, which is loaded through the `META-INF/spring.factories`.

The beans to be loaded are configured on the `DepMessagingAutoconf` class, which is specified on the
`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` file. There, the autoconfigured files
are declared.

## What does it do

This module contains the following:

* The details to connect to the Kafka brokers and the Schema Registry.
* What classes will be used to SerDe the key and body of the messages.
* Enables the observability of the messages sent through Kafka.
