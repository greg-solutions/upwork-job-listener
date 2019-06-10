# Stage 1 (to create a "build" image, ~140MB)
FROM openjdk:8-jdk-alpine3.7 AS builder
RUN java -version

ARG APP_NAME=upwork-job-manager
ARG APP_PATH=./$APP_NAME/build/libs/

COPY . /build
WORKDIR /build

RUN ./gradlew clean build -p $APP_NAME

COPY $APP_PATH/upwork-job-manager.jar /dist/java-application.jar

# Stage 2 (to create a downsized "container executable", ~87MB)
FROM openjdk:8u181-jre-alpine

EXPOSE 5702

COPY --from=builder /dist/java-application.jar ./opt/java-application.jar

WORKDIR /opt/$APP_NAME
## Wait for script (see https://github.com/ufoscout/docker-compose-wait/)

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait /opt/java-application/wait
RUN chmod +x /opt/java-application/wait
CMD /opt/java-application/wait && java -jar /opt/java-application.jar