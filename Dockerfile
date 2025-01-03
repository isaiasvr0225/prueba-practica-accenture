FROM amazoncorretto:8-alpine3.17-jdk
EXPOSE 8089
WORKDIR /root
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root
RUN ./mvnw dependency:go-offline
COPY ./src /root/src
RUN ./mvnw clean package -DskipTests
ENTRYPOINT [ "java", "-jar", "/root/target/prueba-practica-accenture-1.0.0.jar" ]