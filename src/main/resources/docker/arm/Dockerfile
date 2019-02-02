FROM arm32v6/openjdk:8-jre-alpine
VOLUME /tmp
COPY ../../../../../build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
