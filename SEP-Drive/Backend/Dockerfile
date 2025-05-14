# 1. Bau-Stage (hier wird die JAR-Datei erstellt)
FROM maven:3.9.6-eclipse-temurin-17 AS build


# Arbeitsverzeichnis im Container
WORKDIR /app

# Kopiere die pom.xml zuerst (für Caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Kopiere den gesamten Code
COPY src ./src

# Baue die JAR-Datei
RUN mvn package -DskipTests

# 2. Runtime-Stage (hier wird die JAR-Datei ausgeführt)
FROM eclipse-temurin:17

# Arbeitsverzeichnis
WORKDIR /app

# Kopiere die gebaute JAR-Datei
COPY --from=build /app/target/*.jar app.jar

# Port, den Spring Boot verwendet
EXPOSE 8080

# Startbefehl
ENTRYPOINT ["java", "-jar", "app.jar"]