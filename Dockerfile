FROM maven:3.8.3-openjdk-17 AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests


FROM openjdk:17
ARG JAR_FILE=target/techchallenge-0.0.1-SNAPSHOT.jar
COPY --from=builder /usr/src/app/${JAR_FILE} /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]