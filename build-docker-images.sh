#!/usr/bin/env bash

DOCKER_IMAGE_PREFIX="ivanfranchin"
APP_VERSION="1.0.0"

EUREKA_SERVER_APP_NAME="eureka-server"
PRODUCER_API_APP_NAME="producer-api"
CATEGORIZER_SERVICE_APP_NAME="categorizer-service"
COLLECTOR_SERVICE_APP_NAME="collector-service"
PUBLISHER_API_APP_NAME="publisher-api"
NEWS_CLIENT_APP_NAME="news-client"

EUREKA_SERVER_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${EUREKA_SERVER_APP_NAME}:${APP_VERSION}"
PRODUCER_API_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${PRODUCER_API_APP_NAME}:${APP_VERSION}"
CATEGORIZER_SERVICE_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${CATEGORIZER_SERVICE_APP_NAME}:${APP_VERSION}"
COLLECTOR_SERVICE_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${COLLECTOR_SERVICE_APP_NAME}:${APP_VERSION}"
PUBLISHER_API_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${PUBLISHER_API_APP_NAME}:${APP_VERSION}"
NEWS_CLIENT_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${NEWS_CLIENT_APP_NAME}:${APP_VERSION}"

SKIP_TESTS="true"

./mvnw clean spring-boot:build-image \
  --projects "$EUREKA_SERVER_APP_NAME" \
  -DskipTests="$SKIP_TESTS" \
  -Dspring-boot.build-image.imageName="$EUREKA_SERVER_DOCKER_IMAGE_NAME"

./mvnw clean spring-boot:build-image \
  --projects "$PRODUCER_API_APP_NAME" \
  -DskipTests="$SKIP_TESTS" \
  -Dspring-boot.build-image.imageName="$PRODUCER_API_DOCKER_IMAGE_NAME"

./mvnw clean spring-boot:build-image \
  --projects "$CATEGORIZER_SERVICE_APP_NAME" \
  -DskipTests="$SKIP_TESTS" \
  -Dspring-boot.build-image.imageName="$CATEGORIZER_SERVICE_DOCKER_IMAGE_NAME"

./mvnw clean spring-boot:build-image \
  --projects "$COLLECTOR_SERVICE_APP_NAME" \
  -DskipTests="$SKIP_TESTS" \
  -Dspring-boot.build-image.imageName="$COLLECTOR_SERVICE_DOCKER_IMAGE_NAME"

./mvnw clean spring-boot:build-image \
  --projects "$PUBLISHER_API_APP_NAME" \
  -DskipTests="$SKIP_TESTS" \
  -Dspring-boot.build-image.imageName="$PUBLISHER_API_DOCKER_IMAGE_NAME"

./mvnw clean spring-boot:build-image \
  --projects "$NEWS_CLIENT_APP_NAME" \
  -DskipTests="$SKIP_TESTS" \
  -Dspring-boot.build-image.imageName="$NEWS_CLIENT_DOCKER_IMAGE_NAME"
