FROM adoptopenjdk:17-jdk-hotspot

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

CMD [
    "java",
    "-jar",
    "target/nome-do-seu-arquivo-jar.jar",
    "--spring.datasource.url=jdbc:mysql://db:3306/database",
    "--spring.datasource.username=user",
    "--spring.datasource.password=password"
]
