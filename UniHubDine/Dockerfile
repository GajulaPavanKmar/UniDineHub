FROM eclipse-temurin:17-jdk-alphine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java", ".jar", "Restaurant-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080 