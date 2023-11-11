FROM eclipse-temurin:17.0.9_9-jdk

EXPOSE 8080

WORKDIR /app
COPY . .

RUN ./gradlew bootJar && cp build/libs/japan-opendata-chatgpt-plugin.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]