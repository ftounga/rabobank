FROM maven:3.6.3-jdk-8-slim
RUN mkdir /usr/src/api
COPY pom.xml /usr/src/api
WORKDIR /usr/src/api
RUN mvn verify --fail-never
COPY . /usr/src/api
RUN mvn clean install -DskipTests
WORKDIR /usr/src/api/target
EXPOSE 8080
CMD ["java", "-jar", "rabobank-0.0.1-SNAPSHOT.jar"]
