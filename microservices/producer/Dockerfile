FROM eclipse-temurin:21.0.2_13-jdk-alpine as builder
ENV folder="./producer"
WORKDIR /opt/app
COPY ${folder}/.mvn/ .mvn
COPY ${folder}/mvnw ./
COPY pom.xml ./
COPY ${folder}/pom.xml ${folder}/pom.xml
RUN ./mvnw dependency:go-offline
COPY ${folder}/src ${folder}/src
RUN ./mvnw clean install

FROM eclipse-temurin:21.0.2_13-jre-alpine
ENV folder="producer"
COPY --from=builder /opt/app/${folder}/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
