# Example of custom Java runtime using jlink in a multi-stage container build
FROM eclipse-temurin:21 AS jre-build

ARG JAR_FILE=target/*.jar
COPY  ${JAR_FILE} /app/app.jar
WORKDIR /app
RUN java -Djarmode=tools -jar app.jar extract
# List jar modules
RUN jar xf app.jar
RUN jdeps \
    --ignore-missing-deps \
    --print-module-deps \
    --multi-release 21 \
    --recursive \
    --class-path 'BOOT-INF/lib/*' \
    app.jar > modules.txt

# Create a custom Java runtime with only needed dependancies
RUN $JAVA_HOME/bin/jlink \
         --add-modules $(cat modules.txt) \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime

# Define your base image
FROM debian:buster-slim
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"
COPY --from=jre-build /javaruntime $JAVA_HOME

# Continue with your application deployment
RUN mkdir /opt/server
COPY --from=jre-build /app/app /opt/server
CMD ["java", "-jar", "/opt/server/app.jar"]




#inspired from https://dzone.com/articles/ways-to-reduce-jvm-docker-image-size