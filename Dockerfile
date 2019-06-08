FROM openjdk:8-jre-slim
EXPOSE 8080
COPY ./build/libs/*.jar /app/strava-cli.jar
WORKDIR /app
CMD java -jar strava-cli.jar
