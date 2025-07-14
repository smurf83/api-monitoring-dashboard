# Task Management API

A comprehensive Spring Boot REST API for managing tasks with full CRUD operations, filtering, search capabilities, and statistics.

## Features

- **Full CRUD Operations**: Create, Read, Update, Delete tasks
- **Advanced Filtering**: Filter tasks by status, priority, due date ranges
- **Search Functionality**: Search tasks by title
- **Task Statistics**: Get task count statistics by status
- **Comprehensive API Documentation**: OpenAPI/Swagger UI integration
- **Data Validation**: Input validation with meaningful error messages
- **Exception Handling**: Centralized error handling with proper HTTP status codes
- **Database Integration**: H2 in-memory database with JPA/Hibernate
- **Pagination Support**: Paginated responses for large datasets
- **Sample Data**: Pre-loaded sample tasks for testing

## Technologies Used

- **Java 17**
- **Spring Boot 3.2.1**
- **Spring Web** - REST API development
- **Spring Data JPA** - Data persistence
- **H2 Database** - In-memory database
- **Lombok** - Reducing boilerplate code
- **OpenAPI 3** - API documentation
- **Maven** - Build and dependency management
- **Bean Validation** - Input validation

## Project Structure

```
task-management-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/taskmanagement/
│   │   │   ├── TaskManagementApiApplication.java
│   │   │   ├── config/
│   │   │   │   ├── DataLoader.java
│   │   │   │   └── OpenApiConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── TaskController.java
│   │   │   ├── model/
│   │   │   │   └── Task.java
│   │   │   ├── repository/
│   │   │   │   └── TaskRepository.java
│   │   │   └── service/
│   │   │       └── TaskService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd task-management-api
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Database Access

- **H2 Console**: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:taskdb`
  - Username: `sa`
  - Password: `password`

### API Documentation

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/api-docs`

## API Endpoints

### Task Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/paginated` | Get all tasks with pagination |
| GET | `/api/tasks/{id}` | Get task by ID |
| POST | `/api/tasks` | Create a new task |
| PUT | `/api/tasks/{id}` | Update a task |
| DELETE | `/api/tasks/{id}` | Delete a task |
| PATCH | `/api/tasks/{id}/status` | Update task status |

### Filtering and Search

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks/status/{status}` | Get tasks by status |
| GET | `/api/tasks/priority/{priority}` | Get tasks by priority |
| GET | `/api/tasks/search?title={title}` | Search tasks by title |
| GET | `/api/tasks/overdue` | Get overdue tasks |
| GET | `/api/tasks/due-between?start={start}&end={end}` | Get tasks due between dates |

### Statistics

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks/statistics` | Get task count statistics by status |

## Task Model

```json
{
  "id": 1,
  "title": "Complete project documentation",
  "description": "Write comprehensive documentation for the task management API",
  "status": "IN_PROGRESS",
  "priority": "HIGH",
  "dueDate": "2024-01-15T10:30:00",
  "createdAt": "2024-01-12T09:00:00",
  "updatedAt": "2024-01-12T09:00:00"
}
```

### Task Status Values
- `PENDING`
- `IN_PROGRESS`
- `COMPLETED`
- `CANCELLED`

### Task Priority Values
- `LOW`
- `MEDIUM`
- `HIGH`
- `URGENT`

## Sample API Requests

### Create a Task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "New Task",
    "description": "Task description",
    "priority": "HIGH",
    "dueDate": "2024-01-20T15:30:00"
  }'
```

### Get All Tasks
```bash
curl http://localhost:8080/api/tasks
```

### Update Task Status
```bash
curl -X PATCH http://localhost:8080/api/tasks/1/status?status=COMPLETED
```

### Search Tasks
```bash
curl http://localhost:8080/api/tasks/search?title=documentation
```

### Get Task Statistics
```bash
curl http://localhost:8080/api/tasks/statistics
```

## Error Handling

The API returns consistent error responses with appropriate HTTP status codes:

```json
{
  "timestamp": "2024-01-12T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Task not found with id: 999"
}
```

## Testing

Run the tests with:
```bash
mvn test
```

## Sample Data

The application comes with pre-loaded sample tasks for testing purposes. The sample data includes tasks with various statuses, priorities, and due dates.

## Configuration

Key configuration properties in `application.properties`:

```properties
# Database
spring.datasource.url=jdbc:h2:mem:taskdb
spring.h2.console.enabled=true

# OpenAPI
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.