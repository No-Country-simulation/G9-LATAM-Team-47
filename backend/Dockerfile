FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

# NUEVA LÍNEA: Limpieza de saltos de línea de Windows (CRLF a LF)
RUN sed -i 's/\r$//' mvnw

RUN chmod +x mvnw
RUN ./mvnw clean package
FROM eclipse-temurin:21-jre
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]