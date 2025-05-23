FROM openjdk:19

COPY target/CarHub.jar  /usr/app/

WORKDIR /usr/app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "CarHub.jar"]
