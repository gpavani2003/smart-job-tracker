# Smart Job Tracker

A full-stack web application that helps users manage and track their job applications in one place.

## Features

- User registration and login
- Add new job applications
- View all personal job applications
- Edit application details
- Update application status
- Delete job applications
- Search applications by company name
- Filter applications by status
- Dashboard with application statistics
- Pagination for application records
- Interview dates, follow-up dates, and notes

## Technologies Used

### Frontend
- React.js
- JavaScript
- HTML
- CSS
- Axios

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- REST APIs
- Maven

### Database
- MySQL

## Application Statuses

- Applied
- Interview Scheduled
- Selected
- Rejected

## Project Structure

```text
smart-job-tracker/
└── src/
    └── main/
        ├── java/
        └── resources/

smart-job-tracker-frontend/
└── src/
    ├── App.js
    ├── App.css
    ├── Login.js
    ├── Register.js
    └── api.js
```

## Setup Requirements

- Java JDK
- Maven
- Node.js and npm
- MySQL
- An IDE such as Spring Tool Suite or IntelliJ IDEA

## How to Run

### 1. Configure MySQL

Create the database:

```sql
CREATE DATABASE jobtrackerdb;
```

Configure your database URL, username, and password in the backend `application.properties` file. Keep real credentials out of GitHub.

### 2. Run the Backend

Open the backend project in your IDE and run the Spring Boot application.

The backend runs at:

`http://localhost:8080`

### 3. Run the Frontend

Open a terminal in the frontend directory:

```bash
cd smart-job-tracker-frontend
npm install
npm start
```

The frontend runs at:

`http://localhost:3000`

## API Overview

The backend provides REST endpoints for:

- User registration
- Job application creation and retrieval
- Updating and deleting applications
- Searching and filtering applications
- Dashboard statistics
- Pagination and date-range queries

## Security Note

The application uses HTTP Basic authentication for protected API endpoints. It is a learning and portfolio project and should be reviewed and hardened before production deployment.

## Author

Pavani Gottiveeti

Java Full Stack Developer — Fresher