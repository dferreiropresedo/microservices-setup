FROM eclipse-temurin:21.0.2_13-jdk-alpine
WORKDIR /opt/app
COPY ./ .
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean install
