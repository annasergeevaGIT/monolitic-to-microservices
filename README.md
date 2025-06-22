# Job Application Microservices

This repository contains a job application microservices system built with Spring Boot. The system is composed of three microservices: **Company Service**, **Job Service**, and **Review Service**. Each microservice is responsible for handling different aspects of the job application process. These services are designed to work together, with each one providing specific functionality.

## Technologies Used

<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,spring,postgres,docker,kubernetes,rabbitmq,aws" />
  </a>
</p>

## Microservices Overview

### 1. **Company Service**
- Manages the company details such as name, location, industry, and more.
- CRUD operations for companies.
- Allows viewing of all companies or a specific company by ID.

### 2. **Job Service**
- Handles job listings including job title, description, salary, and associated company.
- CRUD operations for jobs.
- Allows users to apply for jobs and view details of individual jobs.

### 3. **Review Service**
- Allows reviews of companies, helping job seekers to assess potential employers.
- CRUD operations for reviews.
- Links reviews to a specific company.

## Features

- **Job Listings**: View, add, update, and delete job listings.
- **Company Information**: View, add, update, and delete company details.
- **Reviews**: View and add reviews for companies.
- **Inter-Service Communication**: Communication between microservices using REST API.
- **Service Registry**: Service registry and discovery using Eureka.
- **API Gateway**: API Gateway setup using Spring Cloud Gateway to route requests to appropriate microservices.
- **Fault Tolerance**: Resilience4J for fault tolerance, circuit breaking, and rate limiting.
- **Distributed Tracing**: Zipkin integration for monitoring microservice interactions.
- **Message Queues**: RabbitMQ integration for asynchronous messaging between services.
- **Spring Boot**: To build the microservices.
- **Spring Cloud**: For service registry (Eureka), API gateway (Spring Cloud Gateway), and configuration management (Spring Cloud Config).
- **PostgreSQL**: Database for persisting company, job, and review data.
- **Docker**: To containerize microservices.
- **RabbitMQ**: For message queue integration.
- **Zipkin**: For distributed tracing.
- **Resilience4J**: For fault tolerance, including circuit breaking and rate limiting.
- **JPA/H2 Database**: For development and testing environments.
- **Eureka**: Service registry for inter-service communication.

## Prerequisites

- **Java 17** or later
- **Docker** 
- **PostgreSQL** 
- **RabbitMQ** 
- **Spring Boot** 
- **Maven** 

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/yourusername/job-app-microservices.git

cd job-app-microservices
```
### Set up Docker and PostgreSQL

Before running the application, ensure you have Docker running on your system. 
You will also need to set up a PostgreSQL container for the database.
To set up PostgreSQL, you can use Docker Compose to create the necessary containers:

```bash
docker-compose up
```
