# Docker modularization

In this file you can find all the information related to the docker configuration needed to boot up
the whole system. An example of what can be found is the needed secrets as well as the purpose of
each
file.

## Secrets

The secrets management offered by [Docker](https://docs.docker.com/compose/use-secrets/) will be
used
to do not expose sensitive data. For this, we need to create a folder name `secrets` that will
contain
the next files:

```
docker
├── ....
└── secrets
    └── dev_oauth_microservice_client_secret.txt
```

* **_dev_oauth_microservice_client_secret_**: Client secret of the OAuth client created inside
  Keycloak

## File structure

````
docker
├── README.md
├── common.yml
├── keycloak.yml
└── secrets
    └── ...
````

### common.yml

It contains the generic definitions that will be used across all the remaining docker files.

[common.yml](common.yml)

### keycloak.yml

It generates the needed components to configure a keycloak server. It specifically generates a
keycloak instance with its corresponding postgres database to maintain the needed data.

[keycloak](keycloak.yml)
