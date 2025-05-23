FROM openjdk:17

COPY target/CarHub.jar  /usr/app/

WORKDIR /usr/app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "CarHub.jar"]
