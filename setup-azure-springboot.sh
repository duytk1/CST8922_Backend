#!/bin/bash

# === CONFIGURATION ===
RESOURCE_GROUP="team7-rg"
LOCATION="eastus"
APP_SERVICE_PLAN="team7-plan"
APP_NAME="team7-microservice-app"
MYSQL_SERVER="team7-db-server"
MYSQL_ADMIN="dbadmin"
MYSQL_PASSWORD="P@ssw0rd"   # <-- CHANGE THIS!
DATABASE_NAME="team7db"
JAR_FILE="target/team7-0.0.1-SNAPSHOT.jar"

# === Create Resource Group ===
az group create --name $RESOURCE_GROUP --location $LOCATION

# === Create App Service Plan ===
az appservice plan create \
  --name $APP_SERVICE_PLAN \
  --resource-group $RESOURCE_GROUP \
  --sku B1 \
  --is-linux

# === Create Web App ===
az webapp create \
  --resource-group $RESOURCE_GROUP \
  --plan $APP_SERVICE_PLAN \
  --name $APP_NAME \
  --runtime "JAVA|17-java17"

# === Create Azure MySQL Flexible Server ===
az mysql flexible-server create \
  --resource-group $RESOURCE_GROUP \
  --name $MYSQL_SERVER \
  --admin-user $MYSQL_ADMIN \
  --admin-password $MYSQL_PASSWORD \
  --location $LOCATION \
  --sku-name Standard_B1ms \
  --storage-size 32 \
  --version 8.0 \
  --public-access 0.0.0.0

# === Create Database on Server ===
az mysql flexible-server db create \
  --resource-group $RESOURCE_GROUP \
  --server-name $MYSQL_SERVER \
  --database-name $DATABASE_NAME

# === Set Web App Environment Variables ===
az webapp config appsettings set \
  --resource-group $RESOURCE_GROUP \
  --name $APP_NAME \
  --settings \
  SPRING_DATASOURCE_URL="jdbc:mysql://$MYSQL_SERVER.mysql.database.azure.com:3306/$DATABASE_NAME?useSSL=true&requireSSL=false" \
  SPRING_DATASOURCE_USERNAME="$MYSQL_ADMIN@$MYSQL_SERVER" \
  SPRING_DATASOURCE_PASSWORD="$MYSQL_PASSWORD" \
  SPRING_FLYWAY_ENABLED=true \
  SPRING_FLYWAY_LOCATIONS=classpath:db/migration \
  SPRING_FLYWAY_BASELINE_ON_MIGRATE=true \
  API_SECURITY_TOKEN_SECRET="SuperSecretToken" \
  SERVER_ERROR_INCLUDE_STACKTRACE=always

# === Build the Spring Boot app ===
./mvnw clean package

# === Deploy the JAR to Azure App Service ===
az webapp deploy \
  --resource-group $RESOURCE_GROUP \
  --name $APP_NAME \
  --src-path $JAR_FILE \
  --type jar

echo "✅ Deployment complete. Visit: https://$APP_NAME.azurewebsites.net"
