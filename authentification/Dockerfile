FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copier le JAR construit
COPY target/authentification-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8070

ENTRYPOINT ["java", "-jar", "app.jar"]
