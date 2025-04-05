FROM openjdk:17-jdk

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY . /app

RUN mvn clean package

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

COPY data/products.txt /app/data/products.txt

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
