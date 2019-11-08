Microprofile OpenTracing demo
========================

This project shows how to use Microprofile OpenTracing API with Quarkus.

## Database start up

You can start the Database as Docker image:

```bash
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -p 5432:5432 postgres:10.5
```  

Then start Jaeger Tracing Server with:
```bash
docker run -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 -p 5775:5775/udp -p 6831:6831/udp -p 6832:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 -p 9411:9411 jaegertracing/all-in-one:latest
```


## Start the application

The application can be started using: 

```bash
mvn compile quarkus:dev
```  

Then log into Jaeger Console which is available at: http://localhost:16686  and check that application events are recorded.


