#!/bin/bash

# Remove old Spring Boot container if it already exists
docker rm -f springboot-mongodb-crud-container

# Run Spring Boot container in background
docker run -d \
  --name springboot-mongodb-crud-container \
  --network springboot-network \
  -p 8081:8081 \
  -e SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/arun_mongo_db \
  -e SPRING_DATA_REDIS_HOST=redis \
  -e SPRING_DATA_REDIS_PORT=6379 \
  -e SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
  springboot-mongodb-crud

# Show running containers
docker ps

# API test URL for Postman/browser
echo "Test API: http://localhost:8081/api/employees/10"

# Health check URL
echo "Health: http://localhost:8081/actuator/health"

# To watch logs
echo "Logs: docker logs -f springboot-mongodb-crud-container"
