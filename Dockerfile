FROM openjdk:17
COPY target/authentication-and-authorization.jar authentication-and-authorization.jar
CMD ["java","-jar","authentication-and-authorization.jar"]
