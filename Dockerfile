FROM eclipse-temurin:21.0.2_13-jdk

EXPOSE 8080

WORKDIR /app
COPY . .

RUN ./gradlew bootJar && cp build/libs/japan-opendata-gpts.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]