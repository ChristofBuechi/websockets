#Build jar-file
./gradlew clean build -x test

#Build Image - can be on the remote host, context is the folder of the Dockerfile
docker build -t christofbuechi/eventresponder .

#run image
docker run -m 100MB -d -p 8090:8080 -t christofbuechi/eventresponder

#save image
docker save -o ~/dockerimage.tar christofbuechi/eventresponder
