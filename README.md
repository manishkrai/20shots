# capital-numbers
#Manish Rai

Assignment for capital numbers

This repository contains following projects -

	1. Match Microservice
	
	2. Service Registry (using Eureka)
	
	3. API Gateway
	
Open API being used for documentation - Available at http://localhost:9001/swagger-ui/index.html

http://localhost:9001/api-docs
    
Service names are being resolved using Service Registry.

Added Postman call for following services -

	1. Match Microservice
	
Used postgresSql, NamedParameterJdbcTemplate and Spring Transactions..

Run projects in following orders to consume the api ->

1. service-registry

2. api-gateway

3. match-service

api exposed through api-gateway.. api-gateway is single point for entry that internally use service-registry to resolve the actual service

Create separate .sql file for each sql script into resources/db/queries folder and consumed from services

