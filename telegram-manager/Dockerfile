# Stage 1 (to create a "build" image, ~140MB)
FROM openjdk:8-jdk-alpine3.7 AS builder
RUN java -version

ARG APP_NAME=telegram-manager
ARG APP_PATH=./$APP_NAME/build/libs/
ARG JAR_FILE=$APP_PATH/$APP_NAME.jar
ARG BUILD_ARGS=''

COPY . /dist
WORKDIR /dist

RUN ./gradlew clean build -p $APP_NAME
RUN mv $APP_PATH /dist/

# Stage 2 (to create a downsized "container executable", ~87MB)
FROM openjdk:8u181-jre-alpine

COPY --from=builder /dist/*.jar ./opt/
RUN ls /opt/
WORKDIR /opt/$APP_NAME
## Wait for script (see https://github.com/ufoscout/docker-compose-wait/)

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait /opt/java-application/wait
RUN chmod +x /opt/java-application/wait
CMD /opt/java-application/wait && java -jar /opt/java-application.jar