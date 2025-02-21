## tech-challenge-4-customer-microservice

### Description
This project is a Spring Boot application for managing customer. It includes functionalities for creating and retrieving customers.

### Getting Started

#### Prerequisitesa
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

# Documentação do Microserviço de Produto

## Descrição
Este microserviço é responsável pela gestão de produtos, permitindo a adição e remoção de estoque, além de fornecer informações detalhadas sobre os produtos disponíveis.

## Tecnologias Utilizadas
- **Java 17**: Linguagem utilizada no desenvolvimento.
- **Spring Boot**: Framework para construção do microserviço.
- **Spring Data JPA**: Abstração para interação com o banco de dados relacional.
- **PostgreSQL**: Banco de dados utilizado para persistência dos dados.
- **Docker**: Contêinerização do microserviço e do banco de dados.
- **Maven**: Gerenciador de dependências e build do projeto.
- **Spring Batch**: Framework para processamento de dados em lote, permitindo a leitura, transformação e escrita de grandes volumes de dados de forma eficiente e escalável.

## Estrutura do Projeto
A estrutura do projeto segue a convenção padrão do Spring Boot com a arquitetura Clean Arch, com a adição de arquivos específicos para a contêinerização com Docker.

### 1. **Importação em Lote de Produtos**
- **POST** `/batch/import`
    - **Descrição**: Este endpoint recebe um arquivo contendo os dados dos produtos e os processa em lote.
    - **Parâmetros**:
        - `file`: Arquivo contendo os dados dos produtos em formato CSV.
        - `milliseconds`: Tempo (em milissegundos) para o processamento.
    - **Exemplo de uso**:
        - Este é o primeiro endpoint a ser chamado para testar a aplicação. Você deve enviar um arquivo com dados de produtos e um valor para o parâmetro `milliseconds`.
        - Caso queira um arquivo pronto, pode utilizar o [produtos_fake.csv](docs/produtos_fake.csv)

### 2. **Gerenciamento de Categorias**
- **GET** `/categories`
    - **Descrição**: Retorna uma lista de todas as categorias de produtos.
    - **Resposta**: Lista de objetos `Category`.

### 3. **Gerenciamento de Produtos**
- **POST** `/product/addStock`
    - **Descrição**: Adiciona estoque a um produto.
    - **Parâmetros**: Corpo da requisição contendo `productId` e `quantity`.
    - **Exemplo de uso**:
        - Requisição:
          ```json
          {
            "productId": 1,
            "quantity": 100
          }
          ```

- **POST** `/product/removeStock`
    - **Descrição**: Remove estoque de um produto.
    - **Parâmetros**: Corpo da requisição contendo `productId` e `quantity`.
    - **Exemplo de uso**:
        - Requisição:
          ```json
          {
            "productId": 1,
            "quantity": 50
          }
          ```

- **GET** `/product/{productId}/stock`
    - **Descrição**: Consulta o estoque de um produto específico.
    - **Parâmetros**: `productId` (ID do produto).
    - **Resposta**: Quantidade de estoque do produto.

- **GET** `/product`
    - **Descrição**: Lista todos os produtos.
    - **Resposta**: Lista de objetos `Product`.

### 4. **Mensagem para Remover Estoque**
- **Consumidor (via RabbitMQ)**:
    - **Descrição**: Este endpoint é invocado quando uma mensagem para remover estoque é recebida via RabbitMQ.
    - **Ação**: Executa a remoção de estoque para o produto conforme os dados da mensagem.

## Como Executar

### Passo 1: Clone o repositório
Clone o repositório para sua máquina local:

### Passo 2: Compile o projeto
Compile o projeto utilizando Maven, incluindo todas as dependências:
```sh
mvn clean package -DskipTests
```

### Passo 3: Configure o Docker
Certifique-se de que você tenha o Docker e o Docker Compose instalados. Em seguida, execute o seguinte comando para inicializar os serviços definidos no `docker-compose.yml`:

```sh
docker-compose up --build
```

Isso criará os contêineres para o microserviço e o PostgreSQL. A aplicação estará disponível na porta `8081`.

### Passo 4: Acesse a API
Com os contêineres em execução, você pode acessar a API através da URL `http://localhost:8081`. Utilize as ferramentas de sua escolha (por exemplo, Postman ou cURL) para fazer requisições aos endpoints definidos.

## Executando os Testes

A aplicação possui testes automatizados utilizando RestAssured e JUnit. Para executá-los, utilize:

```sh
mvn test
```

## Documentação da API

A documentação interativa da API (Swagger) pode ser acessada após iniciar a aplicação:

```
http://localhost:8081/swagger-ui/index.html
```

## Licença
Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

# Logistic Microservice

Este é um microserviço de logística desenvolvido em Java utilizando Spring Boot. Ele gerencia entregadores, rastreamento e pedidos, incluindo a criação, atualização e consulta de informações.

## Tecnologias Utilizadas

- **Linguagem**: Java 17
- **Framework**: Spring Boot 3.4.2
- **Banco de Dados**: PostgreSQL (ou H2 para testes)
- **ORM**: Spring Data JPA
- **Testes**: JUnit 5, RestAssured
- **Gerenciamento de Dependências**: Maven
- **Documentação da API**: SpringDoc OpenAPI

## Estrutura do Projeto

- `src/main/java/br/com/fiap/logistic/`
    - `adapter/`: Contém classes de conversão entre diferentes camadas do sistema.
    - `controller/`: Contém os controllers responsáveis pelas requisições HTTP.
    - `domain/`: Contém as classes de domínio do sistema.
    - `dto/`: Contém as classes de Data Transfer Object (DTO).
    - `entity/`: Contém as entidades JPA.
    - `gateway/`: Contém as interfaces de gateway.
    - `gateway/impl/`: Contém as implementações das interfaces de gateway.
    - `repository/`: Contém os repositórios JPA.
    - `usecase/`: Contém os casos de uso das regras de negócio.
    - `service/`: Contém os serviços de negócio.

## Como Executar

### 1. Navegar até o diretório do projeto

```sh
cd logistic-microservice
```

### 2. Configurar o Banco de Dados PostgreSQL

Certifique-se de que um banco de dados PostgreSQL esteja rodando e configure a conexão no `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/logistic_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Caso prefira rodar o banco via Docker, utilize o comando:

```sh
docker run -d --name logistic-db -e POSTGRES_USER=seu_usuario -e POSTGRES_PASSWORD=sua_senha -e POSTGRES_DB=logistic_db -p 5432:5432 postgres:latest
```

### 3. Compilar o projeto

```sh
mvn clean install
```

### 4. Executar a aplicação

```sh
mvn spring-boot:run
```

A aplicação estará acessível em: `http://localhost:8084`

### 5. Outra forma de executar a aplicação seria por meio do Docker

```sh

docker-compose up --build
```

## Endpoints

### Entregadores (`/delivery-person`)

- **POST /delivery-person**: Cria um novo entregador.
- **PUT /delivery-person/assign**: Atribui um entregador a um pedido.
- **PUT /delivery-person/{orderId}/complete**: Marca a entrega como concluída.

### Pedidos (`/orders`)

- **POST /orders**: Cria um novo pedido.
- **GET /orders/{id}**: Retorna as informações de um pedido pelo ID.
- **GET /orders?zipCode=XXXXX-XXX**: Retorna pedidos filtrados por CEP.
- **PUT /orders/{id}/status?status=NEW_STATUS**: Atualiza o status de um pedido.

### Rastreamento (`/trackings`)

- **PUT /trackings/{trackingId}/location?latitude=XX.XXXX&longitude=XX.XXXX**: Atualiza a localização do rastreamento.
- **GET /trackings?latitude=XX.XXXX&longitude=XX.XXXX**: Retorna rastreamentos pela localização.


## Documentação da API

A documentação interativa da API (Swagger) pode ser acessada após iniciar a aplicação:

```
http://localhost:8084/swagger-ui/index.html
```

### Sugestão de como testar a aplicação

1. Criar um entregador
2. Atribuir um entregador a um pedido. O número do pedido pode ser obtido ao criar um pedido por meio do MS de pedidos.
3. Atualizar o status do pedido para `IN_TRANSIT`
4. Atualizar a latitude e longitude do rastreamento
5. Concluir a entrega do pedido

Este é o processo normal dos pedidos
Caso queira testar a busca de pedidos por CEP, basta criar um pedido com um CEP específico e buscar por ele.



## Executando os Testes unitários/integrados

A aplicação possui testes automatizados utilizando RestAssured e JUnit. Para executá-los, utilize:

```sh
mvn test
```

