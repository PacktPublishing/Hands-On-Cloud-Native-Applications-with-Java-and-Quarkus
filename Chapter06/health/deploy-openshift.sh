# Create new Openshift project
oc new-project quarkus-microprofile

# Deploy PostgreSQL application
oc new-app -e POSTGRESQL_USER=quarkus -e POSTGRESQL_PASSWORD=quarkus -e POSTGRESQL_DATABASE=quarkusdb postgresql

# Build native application
mvn package -Pnative -Dnative-image.docker-build=true -DskipTests=true

# Create a new Binary Build named "quarkus-microprofile"
oc new-build --binary --name=quarkus-microprofile -l app=quarkus-microprofile

# Set the dockerfilePath attribute into the Build Configuration
oc patch bc/quarkus-microprofile -p '{"spec":{"strategy":{"dockerStrategy":{"dockerfilePath":"src/main/docker/Dockerfile.native"}}}}'

# Start the build, uploading content from the local folder:
oc start-build quarkus-microprofile --from-dir=. --follow

# Create a new Application, using as Input the "quarkus-microprofile" Image Stream:
oc new-app --image-stream=quarkus-microprofile:latest

# Expose the Service through a Route:
oc expose svc/quarkus-microprofile
