FROM eclipse-temurin:17_35-jdk-alpine
COPY . /
RUN ./mvnw clean package
ENTRYPOINT ["java", "-jar", "target/magic15-1.0-SNAPSHOT.jar"]