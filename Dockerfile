FROM azul/zulu-openjdk-alpine:15.0.2
ARG JAR_FILE=build/libs/kirimaru.jar

ENV TZ=Asia/Tokyo

ENV APP_DIR=/usr/local/app

COPY ${JAR_FILE} ${APP_DIR}
CMD ["java","-jar","/app.jar"]
