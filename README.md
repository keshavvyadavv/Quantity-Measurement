# 🚀 Quantity Measurement App (UC19 - Microservice Architecture)

# Quantity Measurement Microservice Architecture

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.0-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![JWT](https://img.shields.io/badge/JWT-0.12.3-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 📌 Table of Contents

- [Project Overview](#project-overview)
- [Microservice Architecture](#microservice-architecture)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Services Description](#services-description)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [How to Run](#how-to-run)
- [API Endpoints](#api-endpoints)
- [Request & Response Examples](#request--response-examples)
- [Security Flow](#security-flow)
- [Service Communication](#service-communication)
- [Eureka Dashboard](#eureka-dashboard)
- [Swagger UI](#swagger-ui)
- [Environment Configuration](#environment-configuration)
- [Common Errors & Fixes](#common-errors--fixes)

---

## 📖 Project Overview

**Quantity Measurement App** is a fully functional **Spring Boot Microservice Architecture** project that allows users to perform various quantity measurement operations such as **Add, Subtract, Multiply, Divide, Compare and Convert** across multiple measurement categories including **Length, Weight, Volume and Temperature**.

This project was originally built as a **Monolithic Spring Boot Application** and has been **converted into a Microservice Architecture** using:

- **Spring Cloud Netflix Eureka** — Service Registry
- **Spring Cloud Gateway** — API Gateway with JWT Validation
- **Spring Security + JWT** — Authentication & Authorization
- **GitHub OAuth2** — Social Login
- **Spring Data JPA + MySQL** — Persistence Layer
- **OpenFeign** — Declarative REST Client for inter-service communication

---

## 🏗️ Microservice Architecture

```
                        ┌─────────────────────┐
                        │   Frontend (React)   │
                        │    localhost:3000     │
                        └──────────┬──────────┘
                                   │
                                   ▼
                        ┌─────────────────────┐
                        │     API Gateway      │
                        │    localhost:8080     │
                        │  JWT Validation +    │
                        │  Request Routing     │
                        └──────────┬──────────┘
                                   │
                    ┌──────────────┼──────────────┐
                    │              │              │
                    ▼              ▼              │
         ┌──────────────┐  ┌──────────────┐      │
         │   Security   │  │  Quantity    │      │
         │   Service    │  │ Measurement  │      │
         │ localhost:   │  │    App       │      │
         │    8081      │  │ localhost:   │      │
         │              │  │    8082      │      │
         └──────┬───────┘  └──────┬───────┘      │
                │                 │              │
                ▼                 ▼              │
         ┌──────────┐      ┌──────────┐         │
         │security_db│     │quantity_db│         │
         │  (MySQL) │      │  (MySQL) │         │
         └──────────┘      └──────────┘         │
                                                 │
                        ┌────────────────────────┘
                        ▼
               ┌─────────────────────┐
               │    Eureka Server     │
               │    localhost:8761    │
               │  Service Registry   │
               └─────────────────────┘
```

---

## 🛠️ Tech Stack

| Technology              | Version    | Purpose                              |
|-------------------------|------------|--------------------------------------|
| Java                    | 17         | Programming Language                 |
| Spring Boot             | 3.2.0      | Application Framework                |
| Spring Cloud            | 2023.0.0   | Microservice Infrastructure          |
| Spring Cloud Gateway    | 2023.0.0   | API Gateway + Routing                |
| Spring Cloud Eureka     | 2023.0.0   | Service Registry & Discovery         |
| Spring Security         | 6.x        | Authentication & Authorization       |
| Spring Data JPA         | 3.2.0      | ORM & Database Access                |
| Spring OAuth2 Client    | 3.2.0      | GitHub Social Login                  |
| OpenFeign               | 2023.0.0   | Inter-Service Communication          |
| JWT (jjwt)              | 0.12.3     | Token Generation & Validation        |
| MySQL                   | 8.0        | Production Database                  |
| Lombok                  | Latest     | Boilerplate Reduction                |
| Swagger / OpenAPI       | 2.1.0      | API Documentation                    |
| Spring Boot Actuator    | 3.2.0      | Health Checks & Monitoring           |
| Maven                   | 3.8+       | Build Tool                           |

---

## 📁 Project Structure

```
microservice-architecture/
│
├── eureka-server/                          → Service Registry (Port 8761)
│   ├── src/main/java/com/eureka/
│   │   └── EurekaServerApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
│
├── security-service/                       → Auth Service (Port 8081)
│   ├── src/main/java/com/security/
│   │   ├── SecurityServiceApplication.java
│   │   ├── controller/
│   │   │   └── AuthController.java
│   │   ├── service/
│   │   │   ├── JwtService.java
│   │   │   └── CustomUserDetailsService.java
│   │   ├── repository/
│   │   │   └── UserRepository.java
│   │   ├── model/
│   │   │   └── User.java
│   │   ├── dto/
│   │   │   ├── LoginRequest.java
│   │   │   ├── RegisterRequest.java
│   │   │   └── AuthResponse.java
│   │   ├── security/
│   │   │   ├── JwtAuthFilter.java
│   │   │   └── OAuth2SuccessHandler.java
│   │   ├── config/
│   │   │   ├── SecurityConfig.java
│   │   │   └── SwaggerConfig.java
│   │   └── exception/
│   │       ├── GlobalExceptionHandler.java
│   │       ├── DuplicateEmailException.java
│   │       ├── UserNotFoundException.java
│   │       └── ErrorResponse.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
│
├── quantity-measurement-app/               → Core Business Service (Port 8082)
│   ├── src/main/java/com/quantity/
│   │   ├── QuantityMeasurementApplication.java
│   │   ├── controller/
│   │   │   └── QuantityMeasurementController.java
│   │   ├── service/
│   │   │   ├── IQuantityMeasurementService.java
│   │   │   └── QuantityMeasurementServiceImpl.java
│   │   ├── repository/
│   │   │   └── QuantityMeasurementRepository.java
│   │   ├── model/
│   │   │   └── QuantityMeasurementEntity.java
│   │   ├── dto/
│   │   │   ├── QuantityDTO.java
│   │   │   ├── QuantityInputDTO.java
│   │   │   ├── QuantityMeasurementDTO.java
│   │   │   └── OperationType.java
│   │   ├── core/
│   │   │   ├── IMeasurable.java
│   │   │   ├── SupportsArithmetic.java
│   │   │   ├── Quantity.java
│   │   │   ├── LengthUnit.java
│   │   │   ├── WeightUnit.java
│   │   │   ├── VolumeUnit.java
│   │   │   └── TemperatureUnit.java
│   │   ├── config/
│   │   │   ├── SecurityConfig.java
│   │   │   └── SwaggerConfig.java
│   │   └── exception/
│   │       ├── GlobalExceptionHandler.java
│   │       ├── QuantityMeasurementException.java
│   │       ├── DatabaseException.java
│   │       └── ErrorResponse.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
│
└── api-gateway/                            → API Gateway (Port 8080)
    ├── src/main/java/com/gateway/
    │   ├── ApiGatewayApplication.java
    │   ├── filter/
    │   │   └── JwtAuthenticationFilter.java
    │   └── config/
    │       └── GatewayConfig.java
    ├── src/main/resources/
    │   └── application.yml
    └── pom.xml
```

---

## 📋 Services Description

### 1. 🔵 Eureka Server (Port: 8761)
The **Service Registry** — acts as a phonebook for all microservices. Every service registers itself here on startup. The API Gateway queries Eureka to discover the location of other services dynamically.

**Key Features:**
- Service registration and discovery
- Health monitoring via heartbeats
- Dashboard to view all registered services
- Self-preservation disabled (dev mode)

---

### 2. 🟢 Security Service (Port: 8081)
Handles **all authentication and user management** operations. Responsible for user registration, login, JWT token generation and GitHub OAuth2 login.

**Key Features:**
- User registration with validation
- JWT token generation and validation
- GitHub OAuth2 social login
- BCrypt password encryption
- Stateless session management

---

### 3. 🟡 Quantity Measurement App (Port: 8082)
The **core business service** — handles all quantity measurement operations and maintains a complete history of all operations in the database.

**Key Features:**
- Add, Subtract, Multiply, Divide, Compare, Convert quantities
- Supports Length, Weight, Volume and Temperature units
- Generic `Quantity<U>` engine with base unit conversion
- Full operation history stored in MySQL
- Error tracking for failed operations

---

### 4. 🔴 API Gateway (Port: 8080)
The **single entry point** for all client requests. Routes requests to appropriate services, validates JWT tokens globally and handles CORS.

**Key Features:**
- JWT validation for secured routes
- Dynamic routing via Eureka service names
- Global CORS configuration
- Load balanced routing using `lb://`
- Reactive (WebFlux) based — non-blocking

---

## ✅ Prerequisites

Make sure the following are installed:

| Tool        | Version  | Download Link                        |
|-------------|----------|--------------------------------------|
| Java JDK    | 17       | https://adoptium.net/                |
| Maven       | 3.8+     | https://maven.apache.org/            |
| MySQL       | 8.0+     | https://dev.mysql.com/downloads/     |
| IntelliJ    | Latest   | https://www.jetbrains.com/idea/      |
| Postman     | Latest   | https://www.postman.com/             |

---

## 🗄️ Database Setup

Open MySQL Workbench or terminal and run:

```sql
-- Create databases
CREATE DATABASE security_db;
CREATE DATABASE quantity_db;

-- Verify creation
SHOW DATABASES;
```

> **Note:** Tables are auto-created by Hibernate on first run due to `ddl-auto: update`

---

## 🚀 How to Run

### Step 1 — Clone / Open Projects

Open all 4 projects in IntelliJ IDEA as separate projects:
```
File → Open → select each project folder → Open in New Window
```

### Step 2 — Maven Reload

For each project:
```
Right click pom.xml → Maven → Reload Project
```

### Step 3 — Update MySQL Password

In `security-service/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    password: YOUR_MYSQL_PASSWORD   # ← update this
```

In `quantity-measurement-app/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    password: YOUR_MYSQL_PASSWORD   # ← update this
```

### Step 4 — Start Services in Order

> ⚠️ **STRICTLY follow this order**

```
1️⃣  Start eureka-server           → wait for port 8761
2️⃣  Start security-service        → wait for port 8081
3️⃣  Start quantity-measurement-app → wait for port 8082
4️⃣  Start api-gateway             → wait for port 8080
```

### Step 5 — Verify All Services Running

Open browser: `http://localhost:8761`

You should see all 3 services registered:
```
SECURITY-SERVICE           UP (1)
QUANTITY-MEASUREMENT-APP   UP (1)
API-GATEWAY                UP (1)
```

---

## 📡 API Endpoints

> All requests go through **API Gateway on port 8080**

### 🔐 Auth Endpoints (Public — No Token Required)

| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| POST   | `/auth/register`   | Register a new user      |
| POST   | `/auth/login`      | Login and get JWT token  |
| GET    | `/auth/oauth-success` | GitHub OAuth2 login   |

---

### ⚙️ Quantity Endpoints (Secured — JWT Token Required)

| Method | Endpoint                                    | Description                        |
|--------|---------------------------------------------|------------------------------------|
| POST   | `/api/v1/quantities/add`                    | Add two quantities                 |
| POST   | `/api/v1/quantities/subtract`               | Subtract two quantities            |
| POST   | `/api/v1/quantities/multiply`               | Multiply two quantities            |
| POST   | `/api/v1/quantities/divide`                 | Divide two quantities              |
| POST   | `/api/v1/quantities/compare`                | Compare two quantities             |
| POST   | `/api/v1/quantities/convert`                | Convert a quantity to another unit |
| GET    | `/api/v1/quantities/history/operation/{op}` | Get history by operation type      |
| GET    | `/api/v1/quantities/history/type/{type}`    | Get history by measurement type    |
| GET    | `/api/v1/quantities/count/{operation}`      | Get count of successful operations |
| GET    | `/api/v1/quantities/history/errored`        | Get all errored operations         |

---

### 📏 Supported Units

| Category        | Units                                          |
|-----------------|------------------------------------------------|
| **LengthUnit**  | `FEET`, `INCHES`, `YARDS`, `CENTIMETERS`       |
| **WeightUnit**  | `MILLIGRAM`, `GRAM`, `KILOGRAM`, `POUND`, `TONNE` |
| **VolumeUnit**  | `LITRE`, `MILLILITRE`, `GALLON`                |
| **TemperatureUnit** | `CELSIUS`, `FAHRENHEIT`, `KELVIN`          |

---

## 📝 Request & Response Examples

### Register User
```http
POST http://localhost:8080/auth/register
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john@gmail.com",
    "mobileNumber": "9876543210",
    "password": "Password@123"
}
```
```json
"User registered successfully"
```

---

### Login
```http
POST http://localhost:8080/auth/login
Content-Type: application/json

{
    "email": "john@gmail.com",
    "password": "Password@123"
}
```
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "message": "Login successful"
}
```

---

### Add Quantities
```http
POST http://localhost:8080/api/v1/quantities/add
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
Content-Type: application/json

{
    "thisQuantityDTO": {
        "value": 1.0,
        "unit": "FEET",
        "measurementType": "LengthUnit"
    },
    "thatQuantityDTO": {
        "value": 12.0,
        "unit": "INCHES",
        "measurementType": "LengthUnit"
    }
}
```
```json
{
    "thisValue": 1.0,
    "thisUnit": "FEET",
    "thisMeasurementType": "LengthUnit",
    "thatValue": 12.0,
    "thatUnit": "INCHES",
    "thatMeasurementType": "LengthUnit",
    "operation": "add",
    "resultValue": 2.0,
    "resultUnit": "FEET",
    "resultMeasurementType": "LengthUnit",
    "error": false
}
```

---

### Convert Temperature
```http
POST http://localhost:8080/api/v1/quantities/convert
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
Content-Type: application/json

{
    "thisQuantityDTO": {
        "value": 100.0,
        "unit": "CELSIUS",
        "measurementType": "TemperatureUnit"
    },
    "thatQuantityDTO": {
        "value": 0.0,
        "unit": "FAHRENHEIT",
        "measurementType": "TemperatureUnit"
    }
}
```
```json
{
    "operation": "convert",
    "resultValue": 212.0,
    "resultMeasurementType": "TemperatureUnit",
    "error": false
}
```

---

### Get History by Operation
```http
GET http://localhost:8080/api/v1/quantities/history/operation/add
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```
```json
[
    {
        "operation": "add",
        "resultValue": 2.0,
        "resultUnit": "FEET",
        "error": false
    }
]
```

---

## 🔒 Security Flow

```
1. User calls POST /auth/login
        ↓
2. security-service validates credentials
        ↓
3. JWT token generated (valid 24 hours)
        ↓
4. User sends request with token:
   Authorization: Bearer <token>
        ↓
5. API Gateway intercepts request
        ↓
6. JwtAuthenticationFilter validates token
        ↓
7. Valid token → adds X-Auth-User header → forwards to service
   Invalid token → returns 401 Unauthorized immediately
        ↓
8. quantity-measurement-app processes request
   (trusts gateway — no re-validation)
        ↓
9. Response sent back through Gateway to client
```

---

## 🔗 Service Communication

Services communicate via **Eureka Service Discovery** + **Spring Cloud Gateway**:

```
Gateway uses:
  lb://security-service          → Eureka resolves → localhost:8081
  lb://quantity-measurement-app  → Eureka resolves → localhost:8082

lb:// = load balanced
     = Eureka looks up current address
     = works even if port or IP changes
```

**Feign Client** (declarative REST):
```java
@FeignClient(name = "quantity-measurement-app")
public interface QuantityClient {
    @PostMapping("/api/v1/quantities/add")
    QuantityMeasurementDTO add(@RequestBody QuantityInputDTO input);
}
```

---

## 📊 Eureka Dashboard

After starting all services, visit:

```
http://localhost:8761
```

You will see:

```
Application                    AMIs  Availability Zones  Status
-----------------------------------------------------------------------
API-GATEWAY                    n/a   (1)                 UP (1)
SECURITY-SERVICE               n/a   (1)                 UP (1)
QUANTITY-MEASUREMENT-APP       n/a   (1)                 UP (1)
```

---

## 📚 Swagger UI

| Service                  | Swagger URL                              |
|--------------------------|------------------------------------------|
| Security Service         | http://localhost:8081/swagger-ui.html    |
| Quantity Measurement App | http://localhost:8082/swagger-ui.html    |

> Note: API Gateway does not have Swagger (it only routes — no business APIs)

---

## ⚙️ Environment Configuration

### Port Summary

| Service                   | Port |
|---------------------------|------|
| Eureka Server             | 8761 |
| API Gateway               | 8080 |
| Security Service          | 8081 |
| Quantity Measurement App  | 8082 |
| MySQL                     | 3306 |
| Frontend (React)          | 3000 |

### JWT Configuration

```yaml
jwt:
  secret: 5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437
  expiration: 86400000   # 24 hours in milliseconds
```

> ⚠️ **Important:** The same JWT secret must be used in both `security-service` and `api-gateway`

### GitHub OAuth2 Configuration

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: YOUR_GITHUB_CLIENT_ID
            client-secret: YOUR_GITHUB_CLIENT_SECRET
            scope: user:email
```

---

## ❌ Common Errors & Fixes

| Error | Cause | Fix |
|-------|-------|-----|
| `Connection refused 8761` | Eureka not running | Start eureka-server first |
| `401 Unauthorized` | Missing/invalid JWT token | Add `Bearer <token>` in Authorization header |
| `No instances available` | Service not registered with Eureka | Wait 30 seconds after service startup |
| `Access denied for 'root'` | Wrong MySQL password | Update password in `application.yml` |
| `Unknown database` | Database not created | Run `CREATE DATABASE` SQL command |
| `Port already in use` | Another process on same port | Kill process: `taskkill /PID <pid> /F` (Windows) |
| `Could not resolve placeholder` | Wrong yml indentation | Check spaces in `application.yml` |
| `Failed to load ApplicationContext` | Dependency issue | Check pom.xml + Maven reload |

---

## 🗂️ Microservice Rules Followed

| Rule | Description | Status |
|------|-------------|--------|
| Single Responsibility | Each service does one job | ✅ |
| Own Database | Each service has its own DB | ✅ |
| Service Registry | All services register with Eureka | ✅ |
| API Gateway | Single entry point for all traffic | ✅ |
| Load Balanced URLs | `lb://` instead of hardcoded URLs | ✅ |
| Same JWT Secret | Consistent secret across services | ✅ |
| Gateway handles JWT | Downstream services trust gateway | ✅ |
| Unique Ports | Each service on its own port | ✅ |
| Correct Startup Order | Eureka → Services → Gateway | ✅ |
| Unique Package Names | com.security, com.quantity, etc. | ✅ |
| DTOs for Communication | No entity exposure | ✅ |
| Actuator Health Checks | All services have `/actuator/health` | ✅ |
| YAML Configuration | `application.yml` in all services | ✅ |

---

