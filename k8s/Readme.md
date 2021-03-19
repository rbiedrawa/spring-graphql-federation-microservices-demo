# Spring Boot GraphQL Federation Microservices - Kubernetes

## Overview

Kubernetes manifest files with instructions how to set up all necessary components.

## Architecture

![k8s-dashboard](./../_docs/architecture-k8s.png)

## Installation

```
# Create spring-graphql-federation-demo namespace
kubectl create ns spring-graphql-federation-demo

# Deploy Customer Service
kubectl apply -f ./k8s/customer-service/customer-service.configmap.yml -n spring-graphql-federation-demo
kubectl apply -f ./k8s/customer-service/ -n spring-graphql-federation-demo


# Deploy Review Service
kubectl apply -f ./k8s/review-service/review-service.configmap.yml -n spring-graphql-federation-demo
kubectl apply -f ./k8s/review-service/ -n spring-graphql-federation-demo

# Deploy Appolo Gateway
kubectl apply -f ./k8s/apollo-gateway/ -n spring-graphql-federation-demo
```

## Port Forward commands
```
# Customer Service
kubectl port-forward -n spring-graphql-federation-demo svc/customer-service 8080:8080 

# Review Service
kubectl port-forward -n spring-graphql-federation-demo svc/review-service 8081:8080 

# Apollo Getway
kubectl port-forward -n spring-graphql-federation-demo svc/apollo-gateway 4000:4000
```

## Uninstall

```
kubectl delete all --all -n spring-graphql-federation-demo
kubectl delete ns spring-graphql-federation-demo
```