#!/bin/bash

API_URL="http://localhost:8080/api/incidents"

# Loop to create 10 incidents
for i in {1..10}; do
    echo "Creating incident #$i..."

    # Create a new incident with dynamic data (you can vary fields if needed)
    response=$(curl -X POST "$API_URL" \
        -H "Content-Type: application/json" \
        -d "{
            \"title\": \"Server Outage #$i\",
            \"description\": \"Server was down for $i hours\",
            \"status\": \"Open\"
        }")

    # Extract the ID of the created incident
    incident_id=$(echo "$response" | jq -r '.id')

    # Output the response and ID
    echo "Response: $response"
    echo "Incident #$i created with ID: $incident_id"
    echo ""
done
