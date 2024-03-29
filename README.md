# Task manager
This project is a task management system that combines user registration and authentication functionality with the ability to create, read, update, and delete tasks. It involves interaction between the Angular frontend and the Spring Boot backend, with JWT authentication implemented for security and session continuation.

# Features

- `Backend Functionality:`
  - Enables CRUD operations to handle user registration, authentication, and task management following RESTful principles; 
  - Provides the ability to update task status and priority, allowing users to modify task attributes dynamically based on their current needs;
  - Implementing JWT authentication ensures a secure user access mechanism and facilitates a smooth session continuation, adding an extra layer of security to the task management system;


- `Frontend Interface:`
  - Seffortlessly register, log in, and access your personalized profile, featuring editable user data;
  - Displays a list of all tasks with basic information about each task and the ability to add/remove tasks;
  - Access to the page with detailed information about the task with the ability to edit the data;

# Technologies
- Java `17`
- Spring Boot `3.1.5`
- MySQL `8.0.32`
- Docker `24.0.2`
- Liquibase `4.20.0`
- Lombok
- Maven `3.8.7`
- Angular: `16.2.12`
- Angular CLI: `16.1.8`


# How to Run
1. Install [Docker](https://www.docker.com/get-started) on your machine
2. Clone remote repository to your local machine
3. In the src/main/resources/application.properties and .env set your credentials
4. Build The application by running the Maven package command: mvn clean package
5. Run the command :
```bash
   docker-compose up --build
```
6. Open your browser at `http://localhost:4202`
7. After starting the application, you can access the Swagger UI documentation for your API by visiting [Swagger](http://localhost:6969/swagger-ui/index.html).