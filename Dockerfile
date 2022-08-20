FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/reward-points-service-1.0.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]