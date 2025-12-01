FROM eclipse-temurin:21-jdk
COPY build/libs/midterm_sw-0.0.1-SNAPSHOT.jar back.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/back.jar"]
