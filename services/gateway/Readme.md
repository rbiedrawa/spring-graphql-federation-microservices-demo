# Apollo Gateway

Node application that start Apollo Gateway on port 4000 and connects to the two microservices.

You can make queries against the Spring apps directly or run combined queries from the gateway.

## Getting Started

### Installation

1. Install node package `npm install`
2. Start local server `npm run start:local`
3. *Remember to start two required microservices on ports (8080,8081) before starting Apollo Gateway.  

### Usage

* Start service.

* Open [GraphiQL](http://localhost:4000/).

* Write queries and tests the result.

### Build

```
docker build -t rbiedrawa/apollo-gateway:1.0.0 .
```

## Important Endpoints

| Name | Endpoint | 
| -------------:|:--------:|
| `Apollo Gateway Playground UI` | http://localhost:4000/ |
