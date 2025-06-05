# Etapa 1: Build con Maven + Java 24
FROM maven:3.9.0-openjdk-24-ea AS build

WORKDIR /app

# Copiamos pom.xml y código fuente
COPY pom.xml .
COPY src ./src

# Construimos el jar sin tests para acelerar
RUN mvn clean package -DskipTests

# Etapa 2: Imagen runtime con Java 24
FROM openjdk:24-ea-jdk-alpine

WORKDIR /app

# Copiamos el jar generado en la etapa de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Usamos variable PORT para Render (por si cambia)
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
