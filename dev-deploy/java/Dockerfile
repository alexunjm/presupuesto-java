ARG SRC_IMAGE=adn-java-presupuesto-familiar:latest

FROM ${SRC_IMAGE} as build

WORKDIR /app

ARG JAVA_MS_PATH=microservicio
ENV JAVA_MS_PATH=${JAVA_MS_PATH}
RUN chmod +x ./${JAVA_MS_PATH}/gradlew
RUN ./${JAVA_MS_PATH}/gradlew --b ./${JAVA_MS_PATH}/build.gradle clean build

FROM public.ecr.aws/amazoncorretto/amazoncorretto:17

ARG JAVA_APP_JAR_PATH=microservicio/build/libs
ENV JAVA_APP_JAR_PATH=${JAVA_APP_JAR_PATH}
ARG JAVA_APP_JAR_NAME=PresupuestoFamiliar-0.0.1-SNAPSHOT.jar
ENV JAVA_APP_JAR_NAME=${JAVA_APP_JAR_NAME}

WORKDIR /app-built

COPY --from=build /app/${JAVA_APP_JAR_PATH} .

CMD ["sleep", "infinity"]
