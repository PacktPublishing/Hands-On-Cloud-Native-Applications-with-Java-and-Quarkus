mvn package -Pnative -Dnative-image.docker-build=true

oc new-project quarkus-customer-service

# Binary Build definition 
oc new-build --binary --name=quarkus-customer-service -l app=quarkus-customer-service

# Add the dockerfilePath location to our Binary Build
oc patch bc/quarkus-customer-service -p '{"spec":{"strategy":{"dockerStrategy":{"dockerfilePath":"src/main/docker/Dockerfile.native"}}}}'

# Uploading directory "." as binary input for the build
oc start-build quarkus-customer-service --from-dir=. --follow

# Create a new application using as source the Binary Build
oc new-app --image-stream=quarkus-customer-service:latest

# Create a Route for external clients
oc expose svc/quarkus-customer-service
