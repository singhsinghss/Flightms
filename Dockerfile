FROM amazoncorretto:17
WORKDIR /app
COPY build/libs/flightms.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]







