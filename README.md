# Microservices setup

This is a project based on the following Udemy course that I wanted to replicate: _Event-Driven
Microservices: Spring Boot, Kafka and Elastic_.
Thus, it basically replicates the same structure followed in this course by I have adapted it to my
personal tastes.

## Boot up

In order to make all this work, it is only needed to execute the following command on the root
directory of the project:

```
docker compose up -d --build
```

> [!NOTE]
> The first execution may take a while as it needs to download all the dependencies. If further
> information is needed, you can check the specific docker [README](docker/README.md)

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