
---

# Project Manager API

## Description
The Project Manager API is a Spring Boot-based application that provides endpoints for managing projects. It allows users to create, retrieve, update, and delete projects.

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Apache Maven

### Steps to Run the Project
1. Clone the repository: `git clone https://github.com/yourusername/project-manager.git`
2. Navigate to the project directory: `cd project-manager`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

## API Usage

### Endpoints
- **Create a New Project**
  - URL: `/app/v1/project/create`
  - Method: POST
  - Request Body: JSON object with project details (name, description, startDate, endDate)
  - Response: Status code and created project details

- **Get All Projects**
  - URL: `/app/v1/project`
  - Method: GET
  - Response: Status code and list of projects

- **Get Project by ID**
  - URL: `/app/v1/project/{id}`
  - Method: GET
  - Response: Status code and project details

- **Update Project by ID**
  - URL: `/app/v1/project/update/{id}`
  - Method: PUT
  - Request Body: JSON object with updated project details
  - Response: Status code and updated project details

- **Delete Project by ID**
  - URL: `/app/v1/project/delete/{id}`
  - Method: DELETE
  - Response: Status code and success message

### Example Requests
- Create a new project:

  -------bash-------
  
  curl -X POST http://localhost:8080/app/v1/project/create -H "Content-Type: application/json" -d '{"name":"Project ABC", "description":"Description of project ABC", "startDate":"2023-01-01", "endDate":"2023-12-31"}'
  ```

## Additional Information

- **Authentication**: No authentication required.
- **Error Handling**: The API returns appropriate HTTP status codes for success and failure scenarios.
- **Swagger UI Documentation**: Access the Swagger UI documentation at [Swagger UI](http://localhost:8080/swagger-ui.html)

---
