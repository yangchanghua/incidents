#!/bin/bash

NETWORK_NAME="incidents-network"

if [ -z $(docker network ls --filter name=$NETWORK_NAME -q) ]; then
  echo "Network not found, creating ..."
  docker network create $NETWORK_NAME
else
  echo "Network $NETWORK_NAME already exists."
fi

CONTAINER_NAME="incidents-postgres"

# Check if the container exists
if [ "$(docker ps -a --filter name=^/${CONTAINER_NAME}$ --format '{{.Names}}')" == "$CONTAINER_NAME" ]; then
  echo "Container $CONTAINER_NAME exists."

  # Check if the container is running
  if [ "$(docker inspect -f '{{.State.Running}}' $CONTAINER_NAME)" == "true" ]; then
    echo "Container $CONTAINER_NAME is already running."
  else
    echo "Starting the container $CONTAINER_NAME."
    docker start $CONTAINER_NAME
  fi

else
  echo "Container $CONTAINER_NAME does not exist. Creating and running it."
  # Create and run the container if it doesn't exist
  docker run -d \
  --network=$NETWORK_NAME \
  --name $CONTAINER_NAME  \
  -e POSTGRES_USER=myuser \
  -e POSTGRES_PASSWORD=mypassword \
  -e POSTGRES_DB=mydatabase \
  -v $(pwd)/init.sql:/docker-entrypoint-initdb.d/init.sql \
  -p 5432:5432 \
  postgres:15-alpine
fi

