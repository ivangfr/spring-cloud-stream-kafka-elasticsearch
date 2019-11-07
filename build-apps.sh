#!/usr/bin/env bash

./mvnw clean package dockerfile:build -DskipTests --projects eureka-server
./mvnw clean package dockerfile:build -DskipTests --projects producer-api
./mvnw clean package dockerfile:build -DskipTests --projects categorizer-service
./mvnw clean package dockerfile:build -DskipTests --projects collector-service
./mvnw clean package dockerfile:build -DskipTests --projects publisher-api
./mvnw clean package dockerfile:build -DskipTests --projects news-client
