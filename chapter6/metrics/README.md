Microprofile Metrics demo
========================

This project shows how to use Microprofile Metrics API with Quarkus.

## Database start up

You can start the Database as Docker image:

```bash
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=quarkus -e POSTGRES_PASSWORD=quarkus -e POSTGRES_DB=quarkusdb -p 5432:5432 postgres:10.5
```

## Start the application

The application can be started using: 

```bash
mvn compile quarkus:dev
```  

Then check the following metrics:

* http://localhost:8080/metrics : This endpoint will return all the metrics, including the system metrics where the application is running.
* http://localhost:8080/metrics/application : This endpoint will just return metrics emitted by the applications deployed.



