# Docker modularization

In this folder you can find multiple docker files with the different needed docker images defined.
It is modularized in multiple files depending on what is the purpose of the docker image, them being
explained in the next sections.

## How to make it work

To make it work it is as simple as executing the next command:

```
docker-compose up -d --build
```

as it will build the corresponding images of the microservices as well.

> [!TIP]
> If you do not want to load all the docker images at once, you can configure what files are loaded 
> on the [.env](.env) file, at the property `COMPOSE_FILE`

## Files

### common.yml

In this file you will find generic definition of docker components need across all the infrastructure

[common.yml](common.yml)

### microservices.yml

The main microservices of the infrastructure can be found on this file.

[microservices.yml](microservices.yml)
