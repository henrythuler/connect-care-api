# Connect Care API

An API for patients and doctors to make and manage appointments, built with Java and Spring Boot, featuring JWT Authentication, PostgreSQL integration, and interactive API documentation via Swagger.

## Project Overview

- **Technologies**: Java, Spring Boot, PostgreSQL, JWT Authentication, Swagger and Flyway
- **Purpose**: To provide a secure and efficient solution for managing and making doctor appointments.

## Prerequisites

- **JDK 21 or higher**
- **Apache Maven Version 3.8.1 or higher**
- **PostgreSQL**: Running PostgreSQL instance.

## Getting Started

1. **Clone the repository**:

```bash
   git clone https://github.com/henrythuler/connect-care-api
   cd connect-care-api
```

2. **Configure application.properties**:

   - Update the `src/main/resources/application.properties` file with your PostgreSQL database credentials and a JWT KEY.

```
spring.datasource.url=jdbc:postgresql://your_instance_url/your_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

jwtKey=your_jwt_key
```

3. **Run the Application**:

- Inside the project folder, open your terminal and run:

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Database Diagram

Below is the database schema for managing doctors, patients, its health insurances, dependents and appointments:

![db_connect_care_diagram](https://github.com/user-attachments/assets/afc06b81-39d4-4870-85ea-99222f11aa4e)


## API Documentation

Access the Swagger UI for API documentation at:
`http://localhost:8080/swagger-ui`

This provides an interactive interface for exploring available endpoints, including expected inputs and responses.

## Dependencies

The project uses the following dependencies:
- **Spring Boot** for application and dependency management.
- **PostgreSQL** driver for database integration.
- **JWT** for token-based authentication.
- **Swagger** for API documentation.
- **Flyway** for db migrations.

Dependencies are managed via Maven: running `mvn install` will download everything required.
