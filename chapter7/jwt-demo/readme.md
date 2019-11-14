# Quarkus Keycloak Demo

Example of OpenID secured Client with JWT authentication. JWTs are issued by Keycloak and contain
claims with general user information as well as current user roles.

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
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=quarkus -e POSTGRES_PASSWORD=quarkus -e POSTGRES_DB=quarkusdb -p 5432:5432 postgres:10.5
```

## Import the Realm quarkus-real.json from Keycloak

## Run the application Tests
```
mvn install
```

