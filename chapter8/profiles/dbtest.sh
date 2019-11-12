
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_Test -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgresTest -e PGPORT=6432 -p 6432:6432 postgres:10.5
