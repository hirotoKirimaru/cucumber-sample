#gradlew clean
#gradlew build

docker build --build-arg JAR_FILE=build/libs/kirimaru.jar -t kirimaru .
