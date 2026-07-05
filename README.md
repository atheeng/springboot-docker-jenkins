# Spring Boot MongoDB CRUD

## Requirements
- Java 17+
- Maven
- MongoDB running locally

## MongoDB Config
Edit:

```properties
src/main/resources/application.properties
```

Default:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/arun_mongo_db
```

## Run

```bash
mvn spring-boot:run
```

App runs on:

```text
http://localhost:8081
```

## APIs

### Create
POST `http://localhost:8081/api/employees`

```json
{
  "name": "Arun",
  "email": "arun@example.com",
  "salary": 50000
}
```

### Get All
GET `http://localhost:8081/api/employees`

### Get By ID
GET `http://localhost:8081/api/employees/{id}`

### Update
PUT `http://localhost:8081/api/employees/{id}`

### Delete
DELETE `http://localhost:8081/api/employees/{id}`
