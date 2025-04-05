FROM openjdk:17-jdk

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

COPY data/products.txt /app/data/products.txt

ENV PRODUCTS_FILE_PATH=/app/data/products.txt

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
