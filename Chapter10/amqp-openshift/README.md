Stock Trade Demo using Quarkus and Artemis AMQ on Openshift
========================

This project shows how to use AMQ Broker with MicroProfile Reactive Messaging.

## Build the native application

The application can be built using: 

```bash
mvn clean package -Pnative -Dnative-image.docker-build=true

```  

## Deploy on Openshift

Follow the instructions on Chapter 10 to deploy the application on Openshift

Then, open your browser to the Route Address, and you should see the Stock Trade Demo.

