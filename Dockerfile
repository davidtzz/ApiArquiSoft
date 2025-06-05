# Etapa 1: Build con Maven + Java 24 (usando Amazon Corretto)
FROM amazoncorretto:24 as jdk-base

# Instalamos Maven manualmente
RUN yum update -y && \
    yum install -y maven && \
    yum clean all

WORKDIR /app

# Copiamos código y pom
COPY pom.xml .
COPY src ./src

# Compilamos la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecución
FROM amazoncorretto:24

WORKDIR /app

# Copiamos el .jar generado
COPY --from=jdk-base /app/target/*.jar app.jar

EXPOSE 8080

# Render asigna dinámicamente el puerto como ${PORT}
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
