FROM openjdk:14-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} Knjizara-Rest-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/Knjizara-Rest-0.0.1-SNAPSHOT.war"]