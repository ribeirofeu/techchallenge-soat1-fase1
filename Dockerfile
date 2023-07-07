FROM maven:3.9.3-amazoncorretto-17

WORKDIR /app

COPY . .

RUN mvn dependency:go-offline

RUN mvn package -DskipTests

CMD ["java", "-jar", "target/techfood-0.0.1-SNAPSHOT.jar"]
