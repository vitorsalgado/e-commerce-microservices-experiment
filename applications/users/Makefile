SHELL := /bin/bash

.DEFAULT_GOAL := help
.PHONY: help
help:
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

up: ## Run the project using Docker Compose
	@docker-compose -f ./deployments/docker-compose.yml up

.PHONY: dev
dev: ## Run local dev environment
	@docker-compose -f ./deployments/docker-compose-integrations.yml up

down: ## Stop and kill all Docker containers
	@docker-compose -f ./deployments/docker-compose.yml down --remove-orphans --volumes
	@docker-compose -f ./deployments/docker-compose.yml rm -f
	@docker-compose -f ./deployments/docker-compose-integrations.yml down --remove-orphans --volumes
	@docker-compose -f ./deployments/docker-compose-integrations.yml rm -f

rebuild: ## Stop, remove and rebuild all Docker containers
	@docker-compose -f ./deployments/docker-compose.yml down --remove-orphans --volumes
	@docker-compose -f ./deployments/docker-compose.yml --verbose build

swagger:
	docker run -p 3000:8080 -e SWAGGER_JSON=/foo/swagger.yaml -v $$(pwd)/api:/foo swaggerapi/swagger-ui
