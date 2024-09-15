NETWORK_NAME="incidents-network"

docker run --rm -d --name incidents-server \
  --network=$NETWORK_NAME \
  -e POSTGRES_HOST=incidents-postgres \
  -e POSTGRES_USER=myuser \
  -e POSTGRES_PASSWORD=mypassword \
  -p 8080:8080 \
  incident-server-app
