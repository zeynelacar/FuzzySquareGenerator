
FROM openjdk:17-jdk-slim

MAINTAINER Zeynel Acar
ADD target/QrGenerator-0.0.1-SNAPSHOT.jar QrGenerator.jar

ENTRYPOINT ["java","-jar","QrGenerator.jar"]
