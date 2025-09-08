# ToyLand_BE

## Introduction
ToyLand_BE is a robust e-commerce application designed to provide a seamless online shopping experience. Built with a microservices architecture, it offers features such as user management, product browsing, shopping cart functionality, order processing, and administrative tools. The application is optimized for scalability and performance, utilizing modern technologies to ensure a reliable and efficient platform for both users and administrators.

---

### Table of Contents
I. [Architecture Overview](#architecture-overview)

II. [Technologies Used](#technologies-used)

III. [Microservices Description](#microservices-description)

IV. [Setup and Installation](#setup-and-installation)

---

<div id="architecture-overview"></div>

## I. Architecture Overview

---

## II. Technologies Used

| Technology            | Purpose                          |
|-----------------------|----------------------------------|
| **Spring Boot 3**     | Backend framework for services  |
| **Kafka**             | Asynchronous messaging system   |
| **Brevo**             | Email and notification service  |
| **mongoDB**           | NoSQL database for flexible and scalable data storage|
| **MySQL**             | Relational database (exam data) |
| **Docker**            | Containerization and deployment |
| **Cloudinary**        | image storages                  |
| **Redis**             | caching data                    |

---

## III. Microservices Description

### 1. Gateway
- **Purpose**: Routes external requests to appropriate microservices.
- **Technology**: Spring Cloud Gateway
- **Key Features**:
    - Centralized entry point for all requests.
    - Dynamic routing to microservices.
    - Integrated with Swagger UI for API documentation.

---

## IV. Setup and Installation

### Prerequisites
- **JDK 17**
- **Kafka**
- **mySQL**
- **mongoDB**
- **redis**

### Steps to Run the Application
1. **Clone the repository**:
   ```bash
   git https://github.com/anhtu301003/ToyLand_BE.git
   cd ToyLand_BE
   ```
---

## Contributors
- **anhtu301003** - [GitHub](https://github.com/anhtu301003)

---

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
