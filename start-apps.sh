#!/usr/bin/env bash

source scripts/my-functions.sh

echo
echo "Starting eureka..."

docker run -d --rm --name eureka -p 8761:8761 \
  --network spring-cloud-stream-kafka-elasticsearch_default \
  --health-cmd='[ -z "$(echo "" > /dev/tcp/localhost/8761)" ] || exit 1' \
  ivanfranchin/eureka-server:1.0.0

wait_for_container_log "eureka" "Started"

echo
echo "Starting producer-api..."

docker run -d --rm --name producer-api -p 9080:8080 \
  -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e SCHEMA_REGISTRY_HOST=schema-registry -e EUREKA_HOST=eureka -e ZIPKIN_HOST=zipkin \
  --network=spring-cloud-stream-kafka-elasticsearch_default \
  --health-cmd='[ -z "$(echo "" > /dev/tcp/localhost/9080)" ] || exit 1' \
  ivanfranchin/producer-api:1.0.0

wait_for_container_log "producer-api" "Started"

echo
echo "Starting categorizer-service..."

docker run -d --rm --name categorizer-service -p 9081:8080 \
  -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e SCHEMA_REGISTRY_HOST=schema-registry -e EUREKA_HOST=eureka -e ZIPKIN_HOST=zipkin \
  --network=spring-cloud-stream-kafka-elasticsearch_default \
  --health-cmd='[ -z "$(echo "" > /dev/tcp/localhost/9081)" ] || exit 1' \
  ivanfranchin/categorizer-service:1.0.0

wait_for_container_log "categorizer-service" "Started"

echo
echo "Starting collector-service..."

docker run -d --rm --name collector-service -p 9082:8080 \
  -e ELASTICSEARCH_HOST=elasticsearch -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e SCHEMA_REGISTRY_HOST=schema-registry -e EUREKA_HOST=eureka -e ZIPKIN_HOST=zipkin \
  --network=spring-cloud-stream-kafka-elasticsearch_default \
  --health-cmd='[ -z "$(echo "" > /dev/tcp/localhost/9082)" ] || exit 1' \
  ivanfranchin/collector-service:1.0.0

wait_for_container_log "collector-service" "Started"

echo
echo "Starting publisher-api..."

docker run -d --rm --name publisher-api -p 9083:8080 \
  -e ELASTICSEARCH_HOST=elasticsearch -e EUREKA_HOST=eureka -e ZIPKIN_HOST=zipkin \
  --network=spring-cloud-stream-kafka-elasticsearch_default \
  --health-cmd='[ -z "$(echo "" > /dev/tcp/localhost/9083)" ] || exit 1' \
  ivanfranchin/publisher-api:1.0.0

wait_for_container_log "publisher-api" "Started"

echo
echo "Starting news-client..."

docker run -d --rm --name news-client -p 8080:8080 \
  -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e SCHEMA_REGISTRY_HOST=schema-registry -e EUREKA_HOST=eureka -e ZIPKIN_HOST=zipkin \
  --network=spring-cloud-stream-kafka-elasticsearch_default \
  --health-cmd='[ -z "$(echo "" > /dev/tcp/localhost/8080)" ] || exit 1' \
  ivanfranchin/news-client:1.0.0

wait_for_container_log "news-client" "Started"

printf "\n"
printf "%14s | %37s |\n" "Application" "URL"
printf "%14s + %37s |\n" "--------------" "-------------------------------------"
printf "%14s | %37s |\n" "producer-api" "http://localhost:9080/swagger-ui.html"
printf "%14s | %37s |\n" "publisher-api" "http://localhost:9083/swagger-ui.html"
printf "%14s | %37s |\n" "news-client" "http://localhost:8080"
printf "\n"
