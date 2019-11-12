
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_Dev -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgresDev -p 5432:5432 postgres:10.5

