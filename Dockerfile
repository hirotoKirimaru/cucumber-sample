FROM azul/zulu-openjdk-alpine:15.0.2
ARG JAR_FILE=build/libs/kirimaru.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
