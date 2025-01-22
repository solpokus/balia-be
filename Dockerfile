# Stage 1: Build
FROM maven:3.8-openjdk-17 AS build
WORKDIR /usr/src/app

# Copy Maven configuration and source code
COPY pom.xml .

# Resolve dependencies
RUN mvn dependency:resolve

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Stage 2: Serve
FROM openjdk:21-slim
WORKDIR /app

# Copy the built JAR file from the build stage
#COPY --from=build target/backend-master-0.0.1-SNAPSHOT.jar backend-master-0.0.1-SNAPSHOT.jar
COPY --from=build /usr/src/app/target/*.jar app.jar

#port for the web service
EXPOSE 8181

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]