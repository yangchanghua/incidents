# Use an official Maven image to build the Spring Boot app
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files from the host to the container
COPY . .

RUN mkdir -p /root/.m2
COPY settings.xml /root/.m2/settings.xml

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Use a smaller JDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory for the app
WORKDIR /app

# Copy the JAR file from the previous stage
COPY --from=build /app/target/*.jar /app/app.jar

# Expose the port the application will run on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

