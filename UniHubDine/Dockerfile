# Use the correct base image
FROM eclipse-temurin:17-jdk-alpine

# Optional: Define a volume if you need to persist data
VOLUME /tmp

# Copy the JAR file into the image
COPY target/UniDineHub.jar app.jar

# Define the entry point that runs the application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Document that the service listens on port 8080
EXPOSE 8080
