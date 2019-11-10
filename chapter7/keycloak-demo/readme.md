# Quarkus Keycloak Demo

Example of Keycloak OpenID secured Client

## Run Keycloak
```
docker run --rm  \
   --name keycloak \
   -e KEYCLOAK_USER=admin \
   -e KEYCLOAK_PASSWORD=admin \
   -p 8180:8180 \
   -it jboss/keycloak \
   -b 0.0.0.0 \
   -Djboss.http.port=8180 
```

## Start Database
```
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -p 5432:5432 postgres:10.5
```

## Import the Realm quarkus-real.json from Keycloak

## Run the application Tests
```
mvn install
```


