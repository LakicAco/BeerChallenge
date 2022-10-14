FROM gradle:7.5-jdk-alpine AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
COPY src $APP_HOME/src
RUN gradle clean build
FROM adoptopenjdk/openjdk11:alpine-jre
ENV APP_HOME=/usr/app
ENV ARTIFACT_NAME=beerchallenge-*-SNAPSHOT.war
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}