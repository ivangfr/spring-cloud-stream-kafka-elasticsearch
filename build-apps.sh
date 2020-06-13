#!/usr/bin/env bash

./mvnw clean compile jib:dockerBuild --projects eureka-server
./mvnw clean compile jib:dockerBuild --projects producer-api
./mvnw clean compile jib:dockerBuild --projects categorizer-service
./mvnw clean compile jib:dockerBuild --projects collector-service
./mvnw clean compile jib:dockerBuild --projects publisher-api
./mvnw clean compile jib:dockerBuild --projects news-client
