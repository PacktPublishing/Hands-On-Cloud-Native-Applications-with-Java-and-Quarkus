#Build the native application
mvn clean package -Pnative -Dnative-image.docker-build=true

#Create a new buiild for it 
oc new-build --binary --name=quarkus-kafka -l app=quarkus-kafka

#Patch the Docker.native file 
oc patch bc/quarkus-kafka -p "{\"spec\":{\"strategy\":{\"dockerStrategy\":{\"dockerfilePath\":\"src/main/docker/Dockerfile.native\"}}}}"

#Deploy the application in the build
oc start-build quarkus-kafka --from-dir=. --follow
 
# To instantiate the image as new app
oc new-app --image-stream=quarkus-kafka:latest
 
# To create the route
oc expose service quarkus-kafka