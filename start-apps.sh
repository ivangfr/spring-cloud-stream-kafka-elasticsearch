#!/usr/bin/env bash

source scripts/my-functions.sh

echo
echo "Starting eureka..."

docker run -d --rm --name eureka \
  -p 8761:8761 --network spring-cloud-stream-elasticsearch_default \
  --health-cmd="curl -f http://localhost:8761/actuator/health || exit 1" --health-start-period=30s \
  docker.mycompany.com/eureka-server:1.0.0

wait_for_container_log "eureka" "Started"

echo
echo "Starting producer-api..."

docker run -d --rm --name producer-api \
  -p 9080:8080 --network=spring-cloud-stream-elasticsearch_default \
  -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e SCHEMA_REGISTRY_HOST=schema-registry -e EUREKA_HOST=eureka -e ZIPKIN_HOST=zipkin \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1" --health-start-period=1m \
  docker.mycompany.com/producer-api:1.0.0

wait_for_container_log "producer-api" "Started"

echo
echo "Starting categorizer-service..."

docker run -d --rm --name categorizer-service \
  -p 9081:8080 --network=spring-cloud-stream-elasticsearch_default \
  -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e SCHEMA_REGISTRY_HOST=schema-registry -e EUREKA_HOST=eureka -e ZIPKIN_HOST=zipkin \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1" --health-start-period=1m \
  docker.mycompany.com/categorizer-service:1.0.0

wait_for_container_log "categorizer-service" "Started"

echo
echo "Starting collector-service..."

docker run -d --rm --name collector-service \
  -p 9082:8080 --network=spring-cloud-stream-elasticsearch_default \
  -e ELASTICSEARCH_HOST=elasticsearch -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e SCHEMA_REGISTRY_HOST=schema-registry -e EUREKA_HOST=eureka -e ZIPKIN_HOST=zipkin \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1" --health-start-period=1m \
  docker.mycompany.com/collector-service:1.0.0

wait_for_container_log "collector-service" "Started"

echo
echo "Starting publisher-api..."

docker run -d --rm --name publisher-api \
  -p 9083:8080 --network=spring-cloud-stream-elasticsearch_default \
  -e ELASTICSEARCH_HOST=elasticsearch -e EUREKA_HOST=eureka -e ZIPKIN_HOST=zipkin \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1" --health-start-period=1m \
  docker.mycompany.com/publisher-api:1.0.0

wait_for_container_log "publisher-api" "Started"

echo
echo "Starting news-client..."

docker run -d --rm --name news-client \
  -p 8080:8080 --network=spring-cloud-stream-elasticsearch_default \
  -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e SCHEMA_REGISTRY_HOST=schema-registry -e EUREKA_HOST=eureka -e ZIPKIN_HOST=zipkin \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1" --health-start-period=1m \
  docker.mycompany.com/news-client:1.0.0

wait_for_container_log "news-client" "Started"

printf "\n"
printf "%14s | %37s |\n" "Application" "URL"
printf "%14s + %37s |\n" "--------------" "-------------------------------------"
printf "%14s | %37s |\n" "producer-api" "http://localhost:9080/swagger-ui.html"
printf "%14s | %37s |\n" "publisher-api" "http://localhost:9083/swagger-ui.html"
printf "%14s | %37s |\n" "news-client" "http://localhost:8080"
printf "\n"
