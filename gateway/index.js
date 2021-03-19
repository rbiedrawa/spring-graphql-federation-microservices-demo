const { ApolloServer, gql } = require('apollo-server');
const {ApolloGateway} = require('@apollo/gateway')
const { endpoints } = require("./config");

const gateway = new ApolloGateway({
  serviceList: endpoints,
  experimental_pollInterval: 6000
});

console.log("Configured endpoints: ", endpoints)

const server = new ApolloServer({ gateway, subscriptions:false });
server.listen();