
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_Prod -e POSTGRES_USER=quarkus -e POSTGRES_PASSWORD=quarkus -e POSTGRES_DB=postgresProd -e PGPORT=7432 -p 7432:7432 postgres:10.5
