# NebulaOps

**Enterprise-Grade CI/CD Cloud Platform and Deployment Simulation Engine**

NebulaOps is a full-stack platform built to simulate the complex backend architecture of modern DevOps and deployment platforms (e.g., Vercel, Azure DevOps, and GitHub Actions). It provides a complete environment for developers to register projects, link GitHub repositories, and trigger automated cloud deployments to AWS, Azure, and GCP.

## Key Features
- **JWT Authentication & RBAC**: Fully secure REST API utilizing JSON Web Tokens and Role-Based Access Control (`ADMIN`, `DEVELOPER`, `VIEWER`).
- **Project Management System**: A dedicated module for users to register software projects, configure target cloud environments, and link external Git repositories.
- **Automated Deployment Engine**: A multi-threaded simulation engine that processes asynchronous deployment requests, manages state transitions (`PENDING` ➔ `SUCCESS`), and logs build artifacts.
- **Enterprise Observability**: Built-in monitoring via Spring Boot Actuator, Prometheus metrics, and a custom Grafana Dashboard to track deployment success rates and system health.
- **UI Dashboard**: A highly aesthetic, dark-mode glassmorphism web interface natively served by the Spring Boot backend.
- **Docker & CI/CD Ready**: Includes a multi-stage Dockerfile for containerization and a pre-configured GitHub Actions workflow for automated deployments to Azure App Service.

## Technology Stack
- **Backend:** Java 17, Spring Boot 3, Spring Security, Spring Data JPA
- **Frontend:** Vanilla HTML5, CSS3, ES6 JavaScript
- **Database:** H2 (In-Memory Fallback) / PostgreSQL
- **DevOps:** Docker, Docker Compose, GitHub Actions
- **Observability:** Prometheus, Grafana

## Getting Started (Local Development)

The platform is designed to run seamlessly on a local development environment with zero external dependencies via the embedded H2 Database.

### Prerequisites
- JDK 17+ installed 
- Maven

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/Gizmo678/NebulaOps.git
   cd NebulaOps
   ```
2. Run the application using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Navigate to the UI Dashboard:
   ```text
   http://localhost:8080/
   ```
4. Access the OpenAPI/Swagger Documentation to test raw endpoints:
   ```text
   http://localhost:8080/swagger-ui.html
   ```

## Docker Deployment
To run the production-ready version with PostgreSQL and Redis:
1. Ensure Docker Desktop is running.
2. Initialize the infrastructure:
   ```bash
   docker-compose up -d
   ```

## Architecture Overview
The application features a decoupled backend architecture adhering to standard design patterns, ensuring high cohesion and low coupling across the persistence, service, and web layers.
