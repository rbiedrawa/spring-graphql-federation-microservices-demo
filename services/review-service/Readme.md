# Review Service

Spring Boot application that exposes review domain graphs.

## Getting Started

### Installation

Start spring boot application

```
sh gradlew bootRun
```

### Usage

* Start service.

* Open [GraphQL playground GUI](http://localhost:4000/).

### Build

```
# Clean build jar
sh gradlew clean build

# Build docker image
./gradlew bootBuildImage
```

## Important Endpoints

| Name | Endpoint | 
| -------------:|:--------:|
| `Review Service` | http://localhost:8081/ |
| `GraphiQL` | http://localhost:8081/graphiql |
