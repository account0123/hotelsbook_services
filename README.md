# Hotels Book - Microservices

This project is a sample implementation of a multi-service application using Spring Boot with JPA, Spring Web, and MySQL.

The application is composed of the following services:

- Hotel Service: It provides the main functionality of the application, allowing users to search for available hotels.
- Review Service: It provides the functionality to retrieve reviews.
- Service Service: It provides the functionality to retrieve the services offered by hotels.

The application is designed to be deployed on a Docker container, and it uses Docker Compose to define the necessary services and networks.

## Prerequisites

Before you can run this application, you need to have the following installed:

- Docker with Docker Compose (optional)
- JDK 17

## Running the Services (Docker)

To run the application, follow these steps:

1. Clone the repository and navigate to the project directory.
2. Build the Docker images for the services by running the following command:

```bash
docker-compose build
```

3. Start the Docker containers for the services by running the following command:

```bash
docker-compose up -d
```

## Debugging the Services (Spring Boot)

To debug the application, follow these steps:

1. Install Spring Boot Tools (SBT) extension for your IDE.
2. Automatically the extension will detect the services and create a Spring Boot configuration for each one.
3. Activate 'dev' profile adding the following VM argument:

```bash
--spring.profiles.active=dev
```
