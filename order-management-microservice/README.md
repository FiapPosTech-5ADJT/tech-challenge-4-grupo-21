## tech-challenge-4-order-management

### Description
This project is a Spring Boot application for managing orders. It includes functionalities for creating, retrieving, and processing orders. The application integrates with customer management and stock products services, and it uses RabbitMQ for messaging.

### Getting Started

#### Prerequisites
- Java 17
- Maven
- Docker and Docker Compose

#### Installation
1. Clone the repository:
   ```bash
   git clone git@github.com:FiapPosTech-5ADJT/tech-challenge-4-grupo-21.git
   cd customer-microservice
   ```

2. Build the application:
   ```bash
   mvn clean package -DskipTests
   ```

3. Start the services using Docker Compose:
   ```bash
   docker-compose up -d
   ```

### Configuration
The application configuration is managed through the `application.properties` file. Key configurations include:

- **Database Configuration:**
  ```ini
  spring.datasource.url=jdbc:mysql://localhost:4000/orders_db
  spring.datasource.username=root
  spring.datasource.password=123456
  spring.jpa.hibernate.ddl-auto=update
  ```

- **RabbitMQ Configuration:**
  ```ini
  spring.rabbitmq.host=localhost
  spring.rabbitmq.port=5672
  spring.rabbitmq.username=guest
  spring.rabbitmq.password=guest
  ```

- **External Services:**
  ```ini
  customer-management.url=http://localhost:8080
  stock-products.url=http://localhost:8081
  ```

### Usage
To run the application locally:
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8082`.

### API Endpoints
- **Create Order:**
  ```http
  POST /orders
  ```

- **Get Order by ID:**
  ```http
  GET /orders/{id}
  ```

### Docker
The application can be containerized using the provided `Dockerfile`. The `docker-compose.yml` file sets up the necessary services including MySQL and RabbitMQ.

### Authors and Acknowledgment
Show your appreciation to those who have contributed to the project.

### License
Specify the license under which the project is distributed.

### Project Status
Indicate the current status of the project, such as active development, maintenance mode, or no longer maintained.
