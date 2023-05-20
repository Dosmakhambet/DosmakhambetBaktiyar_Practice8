# define base docker image
FROM openjdk:17
LABEL maintainer="practice8.net"
ADD target/DosmakhambetbBaktiyar_Practice8-0.0.1-SNAPSHOT.jar spring-practice-docker.jar
ENTRYPOINT ["java", "-jar", "spring-practice-docker.jar"]
