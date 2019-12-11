Stock Trade Demo using Quarkus and Kafka on Openshift
========================

This project shows how to use AMQ Broker with MicroProfile Reactive Messaging.

## Kafka cluster

* Deploy the Strimzi Operator 

* Install the cluster and the Kafka Topic with:

```bash
oc create -f strimzi/kafka-cluster-descriptor.yaml
oc create -f strimzi/kafka-topic-queue-descriptor.yaml
```  
 
## Build the application

The application can be built with: 

```bash
mvn clean package -Pnative -Dnative-image.docker-build=true

```  

Follow the instructions on Chapter 10 to deploy the application on Openshift

Then, open your browser to the Route Address and you should see the Stock Trade Demo.

