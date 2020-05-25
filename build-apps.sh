#!/usr/bin/env bash

./mvnw clean package dockerfile:build --projects eureka-server
./mvnw clean package dockerfile:build --projects producer-api
./mvnw clean package dockerfile:build --projects categorizer-service
./mvnw clean package dockerfile:build --projects collector-service
./mvnw clean package dockerfile:build --projects publisher-api
./mvnw clean package dockerfile:build --projects news-client
