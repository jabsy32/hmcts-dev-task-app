# HMCTS Dev Task App

A simple full-stack task management app with:

- **Backend:** Spring Boot, JPA, H2 in-memory database, Gradle
- **Frontend:** React, TypeScript, Vite

---

## Backend

### Folder
`hmcts-dev-test-backend`

### Requirements
- Java 21 (Temurin)
- Gradle (wrapper included)

### Running the backend
From the `hmcts-dev-test-backend` folder, run:

**Windows / PowerShell:**
```bash
./gradlew bootRun
```
**Linux / macOS:**

```bash
./gradlew bootRun
```
The backend will start on:
http://localhost:4000

## H2 Console (in-memory database for tests and development):

The H2 console allows you to view and query the in-memory database used by the application during development or testing.

Access:
http://localhost:4000/h2-console

### Steps to use:

Open the URL above in your browser.

Fill in the login details (these are typical defaults; adjust if your application has different settings):

- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:testdb (replace testdb with your DB name if different)
- Username: sa
- Password: (leave blank unless configured)

Click Connect.

### Once connected, you can:

- Browse tables.
-Run SQL queries.
- Inspect data created during tests or development.

## API Endpoints
GET /tasks/home – Test connection, returns "Welcome"

POST /tasks – Create a new task

Example POST /tasks request body:
```bash
{
  "title": "My Task",
  "description": "Description here",
  "status": "OPEN",
  "dueDate": "2025-12-07T12:00:00"
}
```


## Frontend
### Folder
hmcts-dev-test-frontend

### Requirements
Node.js (v18+ recommended)
npm

### Installing dependencies
```bash

cd hmcts-dev-test-frontend
npm install
```

### Running the frontend
```bash
npm run dev
```

The frontend will start on:
http://localhost:5173

## Pages
### Create Task Page (/)
Fill out task details and submit.

### Task Success Page (/success)
Shows the created task details after submission.

## Notes
The backend uses an H2 in-memory database, which resets on restart. Tasks are not persisted permanently.

Frontend and backend are separate projects in the same repository.

Java version: 21 (Temurin)
