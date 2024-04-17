FROM eclipse-temurin:21.0.2_13-jre-alpine
RUN apk update
RUN apk upgrade
RUN apk add curl
ENV folder="producer"
COPY --from=microservices-setup:latest /opt/app/${folder}/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
