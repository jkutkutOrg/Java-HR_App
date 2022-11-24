#!/bin/bash

source .env

echo "Creating DB..."
docker run -d \
	--name $DB_NAME \
	-p $DB_PORT:5432 \
	-e POSTGRES_PASSWORD=$DB_USR_PASSWD \
	-e POSTGRES_USER=$DB_USR \
	-e POSTGRES_DB=postgres \
	postgres:latest &&
echo "Done!"
