# HostelHub Pro — Mini Project Report

## 1. Project Description
HostelHub Pro is a full-stack hostel operations application for managing residents, room allocation, fees, complaints, visitors, leave requests, mess menus and notices. It provides a responsive operations dashboard and near-real-time updates using Server-Sent Events.

## 2. Technology Stack
- Frontend: HTML5, CSS3, JavaScript
- Backend: Java 21, Spring Boot 3.5.6, REST API
- Persistence: Spring Data JPA, MySQL 8.4; H2 for quick development
- Observability: Spring Boot Actuator, Cloud Logging/Monitoring ready
- Containerization: Docker + Docker Compose
- CI/CD: GitHub Actions
- Registry/Cloud: Google Artifact Registry + Cloud Run + Cloud SQL

## 3. Functional Modules
1. Dashboard and live activity
2. Student directory
3. Room capacity and allocation/deallocation
4. Fee payments and receipts
5. Complaint tracking and resolution
6. Visitor check-in/check-out
7. Leave request approval
8. Mess menu
9. Notice board

## 4. Realtime and SRE Features
- `/api/events/stream` uses SSE to push operational events to connected dashboards.
- Dashboard refreshes every 20 seconds as a fallback.
- `/actuator/health` supports service health checks.
- Actuator metrics/loggers support observability.
- Docker healthcheck verifies MySQL readiness.
- Cloud Run revisions provide controlled rollback capability.

## 5. Local Execution
`docker compose up --build` starts the application and MySQL. The database is exposed at localhost:3307 to avoid clashes with local MySQL; the application connects to the MySQL service internally on 3306.

## 6. DevOps Pipeline
Developer → GitHub → GitHub Actions → automated Maven test/build → Docker image → Artifact Registry → Cloud Run → Cloud SQL → Monitoring/Logging.

## 7. Deployment Evidence to Capture
- GitHub repository and commit history
- Successful GitHub Actions run
- Docker image and container running locally
- Artifact Registry image
- Cloud Run service and URL
- `/actuator/health` showing UP
- Cloud Logging entries
- Cloud Monitoring dashboard/uptime check
- Application screens showing student, room, fee, complaint, visitor and leave workflows

## 8. Limitations
The role selector in the demo UI is not backend authentication. Production use should add Spring Security/OIDC, secret management, audit logs, database backups, stricter validation and authorization.
