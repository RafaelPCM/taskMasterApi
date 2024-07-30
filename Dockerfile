# Etapa 1: Build
FROM maven:3.6.3-openjdk-17-slim AS builder
COPY . /usr/src/app
WORKDIR /usr/src/app

# Comentar
RUN mvn clean package -DskipTests

# Etapa 2: Run
FROM openjdk:17-jdk-slim
ENV TZ="America/Recife"
COPY --from=builder /usr/src/app/target/*jar /usr/src/app/app.jar
WORKDIR /usr/src/app
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]