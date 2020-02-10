FROM openjdk:11-jre-slim-sid
MAINTAINER Lukasz Rys <lukaszrys.java@gmail.com>

ARG JAR_FILE

ADD ./target/${JAR_FILE} /app/account-service.jar
CMD ["java", "-Xmx512m", "-jar", "-Dspring.profiles.active=docker", "/app/account-service.jar"]

EXPOSE 8080