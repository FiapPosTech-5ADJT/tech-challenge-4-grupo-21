# Stage 1: Compiling
FROM maven:3.8.3-openjdk-17 as base
WORKDIR /app
COPY . /app
RUN mvn package -DskipTests

# Stage 2: Execute JAR in Amazon Corretto and Alpine Linux
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=base /app/target/logistic-microservice.jar /app/logistic-microservice.jar
CMD ["java", "-jar", "/app/logistic-microservice.jar"]
