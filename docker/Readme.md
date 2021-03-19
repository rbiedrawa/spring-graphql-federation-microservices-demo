# Spring Boot GraphQL Federation Microservices - Docker

## Overview

Docker compose file with instructions how to set up all necessary components.


## Installation

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

## Uninstall

```
docker-compose down -v
```

## Screenshot

TODO: add 