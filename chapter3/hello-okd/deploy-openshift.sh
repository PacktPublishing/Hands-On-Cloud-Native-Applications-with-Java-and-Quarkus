# Build native application
mvn package -Pnative -Dnative-image.docker-build=true -DskipTests=true

# Create a new Quarkus project
oc new-project quarkus-hello-okd

# Create a new Binary Build named "quarkus-hello-okd"
oc new-build --binary --name=quarkus-hello-okd -l app=quarkus-hello-okd

# Set the dockerfilePath attribute into the Build Configuration
oc patch bc/quarkus-hibernate -p '{"spec":{"strategy":{"dockerStrategy":{"dockerfilePath":"src/main/docker/Dockerfile.native"}}}}'

# Start the build, uploading content from the local folder: 
oc start-build quarkus-hello-okd --from-dir=. --follow

# Create a new Application, using as Input the "quarkus-hello-okd" Image Stream:
oc new-app --image-stream=quarkus-hello-okd:latest

# Expose the Service through a Route:
oc expose svc/quarkus-hello-okd
