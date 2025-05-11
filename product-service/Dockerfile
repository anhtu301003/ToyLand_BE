FROM openjdk:21-slim
WORKDIR /app
COPY target/product-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]