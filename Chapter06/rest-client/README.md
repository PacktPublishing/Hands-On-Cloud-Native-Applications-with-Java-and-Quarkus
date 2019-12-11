Quarkus REST Client Demo
========================

This project shows how to use REST Client API with Quarkus.

## Database start up

You can start the Database as Docker image:

```bash
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=quarkus -e POSTGRES_PASSWORD=quarkus -e POSTGRES_DB=quarkusdb -p 5432:5432 postgres:10.5
```

## Start the Customer Service Application

Start any example which provides /customers and /orders Endpoints such as Chapter5/hibernate

```bash
mvn install quarkus:dev
```  

Then, you can run the test using:

```bash
mvn compile test
```  


