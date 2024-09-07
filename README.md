# Microservices setup

## Motivation

The main objectives of this project are:

* On one side, to create a typical microservices architecture with different artifacts and be able to connect them with 
the **Spring Boot microservices**

* On the other side, to be able to create **dependency modules** which are able to **automatically** configure the needed Spring
Boot configuration properties, just with its importing as a Maven dependency.


> [!WARNING]
> This project is still Work In Progress, so it may be subjected to changes.


## Technologies & Libraries

### Technologies

* **[Kafka](https://kafka.apache.org/)**. In order to manage the sending of events messages, a Kafka cluster with 3 nodes is created with docker
compose.

* **[Keycloak](https://www.keycloak.org/)**. Keycloak will be in charge of managing the OAuth process to be able to connect to the microservice.

* **Grafana, Prometheus, Zipkin & Loki**. With the objective of adding observability in order to monitor the whole
architecture.
  * **[Loki](https://grafana.com/docs/loki/latest/) & [Zipkin](https://zipkin.io/)**. Both technologies are used to be able to trace the logs associated to a specific execution and check
through which artifacts it went.
  * **[Prometheus](https://prometheus.io/)**. It is used to monitor the performance of the microservices implied on the architecture. 
  * **[Grafana](https://grafana.com/)**. Graphical interface that gathers the information of all the previous sources to ease its understanding.

### Libraries

* **[Spring Boot](https://spring.io/projects/spring-boot)**. For the sake of easing the process of setting up the microservices, the Spring Boot framework is used.

* **[Spring Security](https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html)**. In order to add a security layer to the system, Spring Security is the responsible for this. The
objective is to secure with OAuth2 the private endpoints whilst leaving the public ones as so. 

* **[Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/reference/spring-cloud-stream.html)**. With the objective of simplifying the connection to the Kafka brokers as well as the SerDe of
messages sent through the events, Spring Cloud Stream is used since through a few properties it already manages all the
needed components.

* **[Spring Cloud Config](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)**. Spring Config enables a server which will be responsible for managing a se of properties and returns
the merge of them.

* **[Micrometer](https://micrometer.io/)**. This library is an abstraction that ease the integration with the observability system through the
injection of a few dependencies.

* **[Avro](https://avro.apache.org/)**.  Serialization format commonly used on the exchanging of messages in event-oriented
systems.


## Modules

### Internal modules

In this project the internal dependencies are prefixed wih `int-`. These dependencies are intended to be modules that
contain reusable code.

* **[int-common](./microservices/int-common)**. This module is intended to contain utility code common to other modules.
* **[int-mymessage](./microservices/int-mymessage)**. This module is intended to contain the Avro schemas used in the
exchange of messages.

### Auto-configurable modules

In this project the auto-configurable dependencies are prefixed with `dep-`. These dependencies are intended to automatically
add the Spring Boot configuration.

* **[dep-auth](./microservices/dep-auth/README.md)**. This module is intended to automatically configure the OAuth2-related
configuration properties in Spring Boot.
* **[dep-messaging](./microservices/dep-messaging/README.md)**. This module is intended to automatically configure the
Kafka connection, as well as the SerDe.
* **[dep-observability](./microservices/dep-observability/README.md)**. This module is intended to automatically configure
everything needed to connect to the observability-related artifacts.


## Boot up

In order to make all this work, it is only needed to execute the following command on the root
directory of the project:

```
docker compose up -d --build
```

> [!IMPORTANT]
> Since there are a few secrets to be configured previously to boot up the system, please check out
> the [README.md](docker/README.md) file to get to know which one are needed.

## Cleaning up

In case that you want to clean up all the resources created to generate this repository, first
execute
the next command in the same folder as the previous one:

```
docker compose down
```

Lastly, in order to liberate all the reserved space in disk to create the docker components, execute
the next command to cleanse every image in your filesystem:

```
docker system prune -a
```