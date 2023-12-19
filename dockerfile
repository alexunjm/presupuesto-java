FROM gradle:7-alpine

ARG JAVA_APP_PATH=./
ENV JAVA_APP_PATH=${JAVA_APP_PATH}

WORKDIR /app

RUN echo "${JAVA_APP_PATH}"
COPY "${JAVA_APP_PATH}" /app

# RUN ./microservicio/gradlew --b ./build.gradle clean build
# RUN ./microservicio/gradlew --b ./build.gradle clean build

CMD ["sleep", "infinity"]
