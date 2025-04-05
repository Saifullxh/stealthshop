FROM eclipse-temurin:17-jdk

WORKDIR /app


COPY target/*.jar app.jar


RUN mkdir -p data


COPY data/products.txt data/products.txt


ENV PRODUCTS_FILE_PATH=/app/data/products.txt

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]