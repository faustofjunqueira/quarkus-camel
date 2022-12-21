# quarkus-camel Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Items Configurateds

- *Hexagonal Architecture*
- **Unit Test**: `mvn test`
- **Integration Test**: `mvn verify -DskipITs=false`
- *Error Handler*
  - *Authentication - 401*
  - *Authorization - 403*
  - *Business Error - 400 - code: x*
  - *Generic Error - 500*
    - Em dev e test, retornar o stacktrace, em prod NÃO
  - *Validation Error - 400 - code: 1*
  - *NotFound - 404*
- *API HATEOAS and RestFull API*
- **Authorization with RBAC**: Não é o objetivo garantir a autenticação, somente a authorizacao. Configuração no docker compose, após, importar o arquivo `realm-export.json` contido na raiz do projeto. Para gerar o token, deve criar um usuario e atribuir as roles contidas no client `quarkus-camel`. A autenticação deve ser efetuada através do `quarkus-camel-frontend`.
- *Hibernate with Panache, Envers*
- **Flyway**: Em dev e test o flyway é executado normalmente ao iniciar a aplicação. Em Prd, dentro do cluster k8s é necessario utilizar um `initContainer` e invocar a aplicação passando o parametro `flyway:migrate`.
- *Healthcheck* ( Database, APIs, Thrird Party e Application) - K8S
- *Camel*
- *Log*
- *Splunk*
- *New Relic - Micrometer?!*
- *Docker com Native Build*
- *CI*
- *CD*
- Open API

Pendente:

- Teste de Integração com o Keycloak (https://quarkus.io/guides/security-openid-connect#integration-testing-keycloak-devservices)
- Autenticacao Camel com teste

## About the Example Application

It's a todo system using Red Hat Quarkus and Apache Camel.

Features:

1. Save a Todo task
2. List a Todo Task
3. Complete a Todo Task
4. Delete a Todo Task
5. When a Task is created, completed or deleted, the app notify the log system
6. When a Task is created, completed or deleted, the app send an email
7. When a Task is completed or deleted, the app create an audit report to this action
8. Authetication with keycloak for example.
9. To Create, Delete or completed, need authenticated, but, to list, don't need.
10. In list method I can pass a filter, to list just my tasks
11. Detail all audit about one todo task

### Modeling

Table: **Task**

| Field       | Data type           |
|-------------|---------------------|
| id          | pk(uuid)            |
| ownerId     | uuid                |
 | title       | varchar(64)         |
| description | varchar(256)        |
| completedAt | nullable(timestamp) | 
| createdAt   | timestamp           |
| deletedAt   | nullable(timestamp) |

Table: **Audit**

| Field        | Data type                                 |
|--------------|-------------------------------------------|
| id           | pk(id)                                    | 
| userId       | uuid                                      |
| taskId       | uuid                                      |
| auditedAt    | timestamp                                 |
| beforeStatus | nullable(CREATED or COMPLETED or DELETED) |
| afterStatus  | nullable(CREATED or COMPLETED or DELETED) |

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-camel-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
