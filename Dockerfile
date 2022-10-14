FROM maven:3.8.6-openjdk-18

COPY . .

RUN mvn clean install -DskipTests

CMD mvn spring-boot:run
