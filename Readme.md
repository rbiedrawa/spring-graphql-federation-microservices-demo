# Spring Boot GraphQL Federation Microservices Example

## Overview


The repository contains three separate projects:

customer-service: Spring Boot GraphQL service providing the federated Customer type
gateway: Apollo Server acting as the Federated Gateway
review-service: Spring Boot GraphQL service that extends the Customer type with reviews

## About GraphQL
GraphQL is a query language for APIs and a runtime for fulfilling those queries with your existing data.

GraphQL provides a complete and understandable description of the data in your API, gives clients the power to ask for exactly what they need and nothing more, makes it easier to evolve APIs over time, and enables powerful developer tools.


## Build docker images
```
sh buildDockerImages.sh
```

## Start docker-compose

```
# Start microservices
docker-compose up -d customer-service review-service

# Check services startup
docker-compose logs -f customer-service
docker-compose logs -f review-service

# Start Apollo Gateway 
docker-compose up -d apollo-gateway

# Check Logs
docker-compose logs -f apollo-gateway

```

## Test GraphQL services

Open [query editor](http://localhost:4000/)

Write the following query and tests the result.

1)  All customers with reviews (federated query) 
```
query {
  customers{
    id
    firstName
    reviews{
      id
      rating
      message
    }
  }
}
```

2) Add new customer
```
mutation {
  addCustomer(customer: { firstName: "New User" }) {
    id
    firstName
  }
}
```

## References

[GraphQL](https://graphql.org/)

[DGS framework](https://netflix.github.io/dgs/)
