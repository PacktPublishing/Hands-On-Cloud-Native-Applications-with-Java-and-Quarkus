Microprofile Fault Tolerance demo
========================

This project shows how to use Microprofile Fault Tolerance API with Quarkus.

## Database start up

You can start the Database as Docker image:

```bash
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=quarkus -e POSTGRES_PASSWORD=quarkus -e POSTGRES_DB=quarkusdb -p 5432:5432 postgres:10.5
```

## Start the application

The application can be tested with: 

```bash
mvn compile test
```  

A random Resource failure (50%) will cause an error and Open the Circuit.



