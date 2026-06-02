# NebulaOps 🚀

**An Enterprise-Grade CI/CD Cloud Platform and Deployment Simulation Engine**

NebulaOps is a full-stack platform built to simulate the complex backend architecture of modern DevOps and deployment platforms (like Vercel, Azure DevOps, and GitHub Actions). It provides a complete environment for developers to register projects, link GitHub repositories, and trigger automated cloud deployments to AWS, Azure, and GCP.

## ✨ Key Features
- **JWT Authentication & RBAC**: Fully secure REST API utilizing JSON Web Tokens and Role-Based Access Control (`ADMIN`, `DEVELOPER`, `VIEWER`).
- **Project Management System**: A dedicated module for users to register software projects, configure target cloud environments, and link external Git repositories.
- **Automated Deployment Engine**: A multi-threaded simulation engine that processes asynchronous deployment requests, manages state transitions (`PENDING` ➔ `SUCCESS`), and logs build artifacts.
- **Enterprise Observability**: Built-in monitoring via Spring Boot Actuator, Prometheus metrics, and a custom Grafana Dashboard to track deployment success rates and system health.
- **Stunning UI Dashboard**: A highly aesthetic, dark-mode glassmorphism web interface built with Vanilla JavaScript and CSS, natively served by the Spring Boot backend.
- **Docker & CI/CD Ready**: Includes a multi-stage Dockerfile for containerization and a pre-configured GitHub Actions workflow for automated deployments to Azure App Service.

## 🛠️ Tech Stack
- **Backend:** Java 17, Spring Boot 3, Spring Security, Spring Data JPA
- **Frontend:** Vanilla HTML5, CSS3 (Glassmorphism), ES6 JavaScript
- **Database:** H2 (In-Memory Fallback) / PostgreSQL
- **DevOps:** Docker, Docker Compose, GitHub Actions
- **Observability:** Prometheus, Grafana

## 🚀 Getting Started (Local Development)

The platform is designed to run seamlessly on your local machine with zero external dependencies via the embedded H2 Database.

### Prerequisites
- JDK 17+ installed on your machine
- Maven

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/Gizmo678/NebulaOps.git
   cd NebulaOps
   ```
2. Run the application using the Maven wrapper (or your IDE):
   ```bash
   ./mvnw spring-boot:run
   ```
3. Open your browser and navigate to the UI Dashboard:
   ```text
   http://localhost:8080/
   ```
4. Access the OpenAPI/Swagger Documentation to test raw endpoints:
   ```text
   http://localhost:8080/swagger-ui.html
   ```

## 🐳 Docker Deployment
To run the production-ready version with PostgreSQL and Redis:
1. Ensure Docker Desktop is running.
2. Spin up the infrastructure:
   ```bash
   docker-compose up -d
   ```

## 📸 Dashboard Preview
The application features a sleek, responsive dashboard where authenticated users can view real-time deployment statuses.

---
*Built with ❤️ as a demonstration of scalable backend architecture and DevOps automation.*
