FROM maven:3.9.4-eclipse-temurin-17 AS BUILD_IMAGE

WORKDIR /app

COPY . /app

RUN mvn package -DskipTests --no-transfer-progress \
    && apt-get clean \
    && rm -rf /root/.m2

FROM eclipse-temurin:17.0.8.1_1-jre-jammy

COPY --from=BUILD_IMAGE /app/target/*.jar /opt/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]