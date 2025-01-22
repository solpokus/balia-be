FROM openjdk:17-jdk-alpine

# set the working directory
WORKDIR /app

# copy the application jar file
COPY target/*.jar app.jar

#port for the web service
EXPOSE 8080

#run the web service
ENTRYPOINT ["java","-jar","app.jar"]