# Customer Service

Spring Boot application that exposes customer domain graphs.

Application provides the federated Customer type.

## Getting Started

### Installation

Start spring boot application

```
sh gradlew bootRun
```

### Usage

* Start service.

* Open [GraphiQL](http://localhost:8080/graphiql).

* Write the following query and tests the result:
    -  Find all customers with reviews (federated query)
        ```
        query {
          customers{
            id
            firstName
          }
        }
        ```
  
    - Create new customer
        ```
         mutation {
           addCustomer(customer: { firstName: "New User" }) {
             id
             firstName
           }
         }
         ```
      
### Build

```
# Clean build jar
sh gradlew clean build

# Build docker image
sh gradlew bootBuildImage
```

## Important Endpoints

| Name | Endpoint | 
| -------------:|:--------:|
| `Customer Service` | http://localhost:8080/ |
| `GraphiQL` | http://localhost:8080/graphiql |
