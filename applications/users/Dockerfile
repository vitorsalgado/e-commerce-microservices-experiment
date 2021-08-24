FROM gradle:jdk15 as builder
WORKDIR /opt/userapi
COPY --chown=gradle:gradle . .
RUN gradle clean build --no-daemon --no-build-cache -x test

FROM openjdk:15-alpine
WORKDIR /app
COPY --from=builder /opt/userapi/build/libs/*.jar /app/userapi.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/userapi.jar"]
