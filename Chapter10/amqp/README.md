Stock Trade Demo using Quarkus and Artemis AMQ
========================

This project shows how to use AMQ Broker with MicroProfile Reactive Messaging.

## Kafka cluster

Start up AMQ by running `docker-compose up` 

## Start the application

The application can be started using: 

```bash
mvn compile quarkus:dev
```  

Then, open your browser to `http://localhost:8080`, and you should see the Stock Trade Demo.

