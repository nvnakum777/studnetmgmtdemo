# Student Management System

A REST API for a school to manage student admissions, courses, and enrollments — built for the Backend Software Developer assignment at Platform Commons.

## Tech Stack

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA** (Hibernate)
- **Spring Security** with **JWT** authentication
- **PostgreSQL**
- **Springdoc OpenAPI** (Swagger UI)
- **Lombok**

## Features

**Admin**
- Student admission (name, DOB, gender, unique student code, multiple addresses)
- Course management (name, description, type, duration, topics)
- Assign a course to a student
- Search students by name
- Get students assigned to a particular course

**Student**
- Log in using `student_code` + `date_of_birth`
- Update own profile (email, mobile number, parents' names, address)
- View own enrolled courses
- Leave a course

**Security**
- Admin logs in with username/password and receives a JWT
- Student logs in with `student_code` + `date_of_birth` and receives a JWT
- Role-based access control (`ADMIN` / `STUDENT`) enforced on every protected endpoint

## Prerequisites

- JDK 17+
- Maven (or use the included `./mvnw` wrapper — no local Maven install needed)
- PostgreSQL running locally (or a reachable instance)

## Setup

### 1. Create the database

```sql
CREATE DATABASE studentmgmt;
```

### 2. Configure database credentials

Open `src/main/resources/application.yaml` and update if your local Postgres credentials differ from the defaults:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/studentmgmt
    username: postgres
    password: your_password
```

Tables are created/updated automatically on startup (`ddl-auto: update`) — no manual schema setup needed.

### 3. Run the application

Using the Maven wrapper (recommended, no local Maven required):

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application starts on **http://localhost:8080**.

### 4. Default admin account

A default admin is auto-created on first startup (see `AdminInitializer`):

```
username: admin
password: admin123
```

## API Documentation (Swagger)

Once running, view all endpoints interactively at:

```
http://localhost:8080/swagger-ui.html
```

## Testing the API (Postman)

A Postman collection is included: **`StudentManagementSystem.postman_collection.json`**

1. Import it into Postman
2. Run **Auth → Admin Login** first (uses the default admin credentials above) — the JWT is captured automatically into a collection variable
3. Create a student via **Admin - Students → Create Student**, then run **Auth → Student Login** using that student's `studentCode` + `dateOfBirth` — the JWT is captured automatically
4. Run remaining requests in any order; `studentId` / `courseId` are also auto-captured from create responses
5. **Negative / Security Tests** folder verifies role enforcement (401/403) and duplicate-resource conflicts (409)

### Manual auth (without the collection)

Include the JWT on every protected request:

```
Authorization: Bearer <token>
```

- `POST /api/auth/admin/login` — body: `{ "username": "admin", "password": "admin123" }`
- `POST /api/auth/student/login` — body: `{ "studentCode": "STU001", "dateOfBirth": "2000-05-15" }`

## Project Structure

```
src/main/java/com/studentmgmt/
├── entity/        # JPA entities (Student, Address, Course, StudentCourse, Admin)
├── repository/     # Spring Data JPA repositories
├── service/        # Service interfaces
├── service/impl/    # Service implementations
├── controller/      # REST controllers
├── dto/             # Request/response DTOs
├── mapper/          # Manual entity <-> DTO mappers
├── security/        # JWT filter, JWT service, Spring Security config
├── exception/       # Custom exceptions + global exception handler
└── config/          # Swagger config, default admin seeding
```

## Known Limitations

- No automated unit tests included yet.
- No refresh token flow — JWTs expire after 24 hours (configurable via `jwt.expiration` in `application.yaml`); users log in again after expiry.
