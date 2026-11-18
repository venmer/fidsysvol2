# ---- build stage ----
FROM maven:3.6.3-jdk-8 AS build

WORKDIR /build

COPY pom.xml ./
COPY src ./src

RUN mvn -B clean package -Dmaven.test.skip=true

# ---- runtime stage ----
FROM eclipse-temurin:8-jre

WORKDIR /app

COPY --from=build /build/target/spticad.war /app/spticad.war
COPY --from=build /build/target/dependency/jetty-runner.jar /app/jetty-runner.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Denvironment=docker", "-jar", "/app/jetty-runner.jar", "--port", "8080", "/app/spticad.war"]
