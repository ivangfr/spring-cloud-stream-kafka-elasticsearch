#!/usr/bin/env bash

echo "------------"
echo "Create index"
echo "------------"
curl -X PUT localhost:9200/news_v1 -H "Content-Type: application/json" -d @news-mapping-v1.json

echo
echo "------------"
echo "Create alias"
echo "------------"
curl -X POST localhost:9200/_aliases -H 'Content-Type: application/json' \
     -d '{ "actions": [{ "add": {"alias": "news", "index": "news_v1" }}]}'
echo