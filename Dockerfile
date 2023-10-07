FROM eclipse-temurin:17-jdk-alpine

ADD target/Teste-Backend-*.jar Teste-Backend.jar

ENTRYPOINT ["java", "-jar", "/Teste-Backend.jar"]