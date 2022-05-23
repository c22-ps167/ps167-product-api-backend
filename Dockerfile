#
# Set a variable that can be used in all stages.
#
ARG BUILD_HOME=/cap

#
# Gradle image for the build stage.
#
FROM gradle:7.4.1 as build-image

#
# Set the working directory.
#
ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME

#
# Copy the Gradle config, source code, and static analysis config
# into the build container.
#
COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts $APP_HOME/
COPY --chown=gradle:gradle src $APP_HOME/src

#
# Build the application.
#
RUN gradle --no-daemon assemble
# RUN gradle --no-daemon build

#
# Java image for the application to run in.
#
FROM openjdk:11-oracle

#
# Copy the jar file in and name it app.jar.
#
ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
COPY --from=build-image $APP_HOME/build/libs/backend-restful-api-0.0.1-SNAPSHOT.jar app.jar

#
# The command to run when the container starts.
#
ENTRYPOINT java -jar app.jar