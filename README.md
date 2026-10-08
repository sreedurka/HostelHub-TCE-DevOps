# HostelHub TCE 4.3 — Smart Hostel Management System

A polished multi-portal hostel management system built with Spring Boot, MySQL, REST APIs, Docker and a responsive web UI.

## Portals

### Student portal
- Role-based student login
- Dashboard with room, fee and gate status
- Profile and resident information
- Hostel passport / gate movement
- Request permission to go out
- View approval status and mark return/check-in
- Complaints and leave requests
- 1–5 star feedback
- Weekly mess menu
- Events and notices
- Dark mode
- Responsive mobile layout
- Logout

### Admin / Warden portal
- Separate admin login
- Operations dashboard
- Student management
- Room inventory and bed allocation
- Fee status and payment receipts
- Complaint approval → in-progress → resolved
- Leave approval/rejection
- Gate movement approval/rejection and check-in
- Visitor passes and visitor check-in/out
- Weekly mess menu editing
- Notices and events publishing
- Resident feedback dashboard
- Live SSE activity updates
- Logout

## Demo credentials

Student:
- Register: `CSE001`
- Password: `student123`

Other demo students use the same password: `student123`.

Admin:
- Username: `admin`
- Password: `admin123`

## Run with Docker

```powershell
docker compose up --build
```

Open: `http://localhost:8080`

MySQL is exposed on Windows port `3307` and remains on `3306` inside the Docker network.

Health check: `http://localhost:8080/actuator/health`

Stop:

```powershell
docker compose down
```

## Tech stack

- Java 21
- Spring Boot 3.5.6
- Spring Data JPA / Hibernate
- MySQL 8.4
- HTML / CSS / JavaScript
- Docker / Docker Compose
- Spring Boot Actuator
- Server-Sent Events (SSE)

## DevOps-ready

The repository also contains the GitHub Actions workflow, Dockerfile, GCP deployment documentation, architecture diagram and project report template from the original DevOps assignment version.


## Fresh CI/CD setup

GitHub Actions is split into two workflows:
- `.github/workflows/ci.yml` runs automatically on pushes and pull requests. It runs Maven tests, packages the application, and builds the Docker image.
- `.github/workflows/deploy.yml` is manual (`workflow_dispatch`) and is used after Google Cloud Workload Identity and Artifact Registry secrets are configured.

This prevents an unconfigured GCP deployment from causing the normal CI check to fail.

### Demo credentials
- Student: `CSE001` / `student123`
- Admin/Warden: `admin` / `admin123`

### Local URL
`http://localhost:8080`

### Health URL
`http://localhost:8080/actuator/health`
