# Traini8 API

## Description
A Spring Boot REST API for managing government-funded training centers. This application provides endpoints for creating, updating, deleting, and retrieving training centers.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [API Endpoints](#api-endpoints)
- [Usage](#usage)
- [License](#license)

## Features
- Create a new training center
- Retrieve all training centers with optional filtering by city or state
- Retrieve a training center by ID
- Update an existing training center
- Delete a training center by ID

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Lombok
- Jakarta Validation

## Installation

### Prerequisites
- Java 17
- MySQL server
- Maven

### Steps
1. **Clone the repository:**
   ```bash
   git clone git@github.com:ajaysw01/traini8-task.git   ```

2. **Set up the database:**
   - Create a new MySQL database (e.g., `traini8_db`).
   - Update the `application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/traini8_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Create a new Training Center
- **Endpoint:** `POST /api/training-centers`
- **Request Body:**
    ```json
    {
        "centerName": "Training Center A",
        "centerCode": "TC001",
        "address": {
            "street": "123 Main St",
            "city": "Pune",
            "state": "Maharashtra",
            "zipCode": "411001"
        },
        "studentCapacity": 100,
        "coursesOffered": "Java, Python",
        "contactEmail": "info@trainingcentera.com",
        "contactPhone": "1234567890"
    }
    ```
- **Response:**
    ```json
    {
        "id": 1,
        "centerName": "Training Center A",
        "centerCode": "TC001",
        "address": {
            "street": "123 Main St",
            "city": "Pune",
            "state": "Maharashtra",
            "zipCode": "411001"
        },
        "studentCapacity": 100,
        "coursesOffered": "Java, Python",
        "contactEmail": "info@trainingcentera.com",
        "contactPhone": "1234567890",
        "createdOn": "2024-10-07T12:34:56"
    }
    ```

### Get all Training Centers
- **Endpoint:** `GET /api/training-centers`
- **Query Parameters:**
  - `city` (optional): Filter by city
  - `state` (optional): Filter by state
- **Response:**
    ```json
    [
        {
            "id": 1,
            "centerName": "Training Center A",
            // other fields...
        },
        {
            "id": 2,
            "centerName": "Training Center B",
            // other fields...
        }
    ]
    ```

### Get a Training Center by ID
- **Endpoint:** `GET /api/training-centers/{id}`
- **Response:**
    ```json
    {
        "id": 1,
        "centerName": "Training Center A",
        // other fields...
    }
    ```
- **Error Response (if not found):**
    ```json
    {
        "error": "Training Center not found with id 1"
    }
    ```

### Update an existing Training Center
- **Endpoint:** `PUT /api/training-centers/{id}`
- **Request Body:**
    ```json
    {
        "centerName": "Updated Training Center A",
        // other fields...
    }
    ```

### Delete a Training Center
- **Endpoint:** `DELETE /api/training-centers/{id}`
- **Response:** 
    - **Status:** 204 No Content

## Usage
- Use tools like [Postman](https://www.postman.com/) or [curl](https://curl.se/) to test the API endpoints.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

Feel free to modify or expand upon this version as needed! If you have any specific features or functionalities in mind that you'd like to highlight, just let me know!
