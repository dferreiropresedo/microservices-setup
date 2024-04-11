FROM eclipse-temurin:21.0.2_13-jdk-alpine as builder
ENV folder="./config-server"
WORKDIR /opt/app
COPY ${folder}/.mvn/ .mvn
COPY ${folder}/mvnw ./
COPY ./ .
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean install

FROM eclipse-temurin:21.0.2_13-jre-alpine
RUN apk update
RUN apk upgrade
RUN apk add curl
ENV folder="config-server"
COPY --from=builder /opt/app/${folder}/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
