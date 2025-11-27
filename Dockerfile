# build
FROM maven:4.0.0-rc-5-amazoncorretto-21-al2023 as build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

# run
FROM amazoncorretto:21.0.5
WORKDIR /app

COPY --from=build ./build/target/*.jar ./appserver.jar

EXPOSE 9000

ENTRYPOINT exec java -jar appserver.jar