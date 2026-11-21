FROM eclipse-temurin:21.0.13.1_2-jdk

EXPOSE 8080

WORKDIR /app
COPY . .

RUN ./gradlew bootJar && cp build/libs/japan-opendata-gpts.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]