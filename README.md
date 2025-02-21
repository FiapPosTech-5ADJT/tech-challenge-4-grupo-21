## tech-challenge-4-customer-microservice

### Description
This project is a Spring Boot application for managing customer. It includes functionalities for creating and retrieving customers.

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
  spring.datasource.url=jdbc:mysql://localhost:4001/customers_db
  spring.datasource.username=root
  spring.datasource.password=123456
  spring.jpa.hibernate.ddl-auto=create
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.format_sql=true
  ```
  
### Usage
To run the application locally:
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8086`.

### API Endpoints
- **Create Customer:**
  ```http
  POST /customers
  ```

- **Get Customer by ID:**
  ```http
  GET /customers/{id}
  ```

- **Get ALL Customers:**
  ```http
  GET /customers
  ```

### Docker
The application can be containerized using the provided `Dockerfile`. The `docker-compose.yml` file sets up the necessary services including MySQL and RabbitMQ.

### Authors and Acknowledgment
Show your appreciation to those who have contributed to the project.

### License
Specify the license under which the project is distributed.

### Project Status
Indicate the current status of the project, such as active development, maintenance mode, or no longer maintained.
