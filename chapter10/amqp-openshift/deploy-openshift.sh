#Build native image of the project
mvn clean package -Pnative -Dnative-image.docker-build=true
 
# Create a new binary build
oc new-build --binary --name=quarkus-amq -l app=quarkus-amq

# Patch the native file 
oc patch bc/quarkus-amq -p "{\"spec\":{\"strategy\":{\"dockerStrategy\":{\"dockerfilePath\":\"src/main/docker/Dockerfile.native\"}}}}"

# Add project to the build
oc start-build quarkus-amq --from-dir=. --follow
 
# To instantiate the image
oc new-app --image-stream=quarkus-amq:latest
 
# To create the route
oc expose service quarkus-amq