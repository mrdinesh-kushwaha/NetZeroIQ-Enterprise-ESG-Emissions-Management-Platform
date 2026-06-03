# NetZeroIQ — Enterprise Emissions Management Platform

netzeroiq is a multi-tenant ESG emissions tracking platform. It ingests data from SAP exports, utility portals, and travel booking systems (Concur/Navan), normalizes it into CO₂e figures, and routes records through a review-and-approval workflow with a full audit trail.

## Tech Stack

| Layer    | Technology                          |
|----------|-------------------------------------|
| Backend  | **Java 21 + Spring Boot 3.2**       |
| Auth     | JWT (JWT 0.12) via Spring Security |
| Database | PostgreSQL (via Spring Data JPA)    |
| Frontend | React 18 + Vite + Tailwind CSS      |
| CSV      | OpenCSV                             |
| Deployment| Docker, Render.com                 |

---

## ✨ Features 
- JWT Authentication & Role-Based Access Control 
- ESG Dashboard & Carbon Analytics - CSV / JSON Dataset Upload 
- Audit & Review Workflow - Analyst / Reviewer / Admin Roles 
- Real-time Emissions Insights - Docker & Render Deployment Ready 
- H2 Database for Development 
- PostgreSQL for Production 
- Secure Production Configuration


## Project Structure

```
netzeroiq/
├── backend/                         # Spring Boot application
│   ├── pom.xml
│   └── src/main/java/com/netzeroiq/
│       ├── netzeroiqApplication.java
│       ├── config/                  # Security, CORS, Jackson, exception handler
│       ├── controller/              # REST controllers
│       ├── dto/                     # Request/response DTOs
│       ├── model/                   # JPA entities
│       ├── repository/              # Spring Data repositories
│       ├── security/                # JWT filter + util
│       └── service/                 # Business logic (normalization, ingestion, audit)
├── frontend/                        # React + Vite SPA
│   ├── src/
│   │   ├── api/client.js
│   │   ├── components/Layout.jsx
│   │   ├── hooks/useAuth.jsx
│   │   └── pages/
│   │       ├── LoginPage.jsx
│   │       ├── DashboardPage.jsx
│   │       ├── UploadCenterPage.jsx
│   │       ├── ReviewQueuePage.jsx
│   │       ├── RecordDetailPage.jsx
│   │       └── AuditTimelinePage.jsx
│   └── package.json
├── sample_data/
│   ├── sap_sample.csv
│   ├── utility_sample.csv
│   └── travel_sample.json
└── docs/
```

---

## Local Development

### Prerequisites
- Java 21 (e.g. via SDKMAN: `sdk install java 21-tem`)
- Maven 3.9+
- Node.js 20+
- PostgreSQL 15+

### Backend Setup

```bash
cd backend

# Create database
createdb netzeroiq

# Set environment variables (or edit application.properties)
export DB_USER=postgres
export DB_PASSWORD=your_password
export JWT_SECRET=your-secret-key-min-32-chars-long-here

# Run the application
./mvnw spring-boot:run
# API starts on http://localhost:8000
```

### Frontend Setup

```bash
cd frontend
cp .env.example .env
# Edit .env: VITE_API_BASE_URL=http://localhost:8000

npm install
npm run dev
# Runs on http://localhost:5173
```

---

## API Endpoints

| Method | Path                              | Description                        |
|--------|-----------------------------------|------------------------------------|
| POST   | `/api/auth/login`                 | Login — returns JWT access+refresh |
| POST   | `/api/auth/refresh`               | Refresh access token               |
| GET    | `/api/auth/me`                    | Current user info                  |
| GET    | `/api/dashboard/stats`            | Dashboard aggregates               |
| GET    | `/api/data-sources`               | List tenant data sources           |
| POST   | `/api/data-sources`               | Create data source                 |
| GET    | `/api/batches`                    | List upload batches                |
| GET    | `/api/batches/{id}`               | Single batch detail                |
| POST   | `/api/upload/sap`                 | Upload SAP CSV                     |
| POST   | `/api/upload/utility`             | Upload utility CSV                 |
| POST   | `/api/upload/travel`              | Upload travel JSON                 |
| GET    | `/api/records`                    | List normalized records (filterable)|
| GET    | `/api/records/{id}`               | Single record detail               |
| POST   | `/api/records/{id}/review`        | Review single record               |
| POST   | `/api/records/bulk-review`        | Bulk approve/reject                |
| GET    | `/api/decisions`                  | List review decisions              |
| GET    | `/api/audit`                      | Tenant-scoped audit log            |
| GET    | `/api/records/{id}/audit`         | Record-level audit trail           |

---

## Emission Factors

Using DEFRA 2023 / IEA 2022 approximations (kg CO₂e per unit):

- Diesel combustion: 2.6391 kg/litre
- Electricity (UK grid): 0.20707 kg/kWh
- Economy flight short-haul: 0.15530 kg/passenger-km
- Hotel stay: 31.0 kg/room-night

See `NormalizationService.java` for the full table.

---

## User Roles

| Role     | Can Upload | Can Review | Can View Dashboard & Audit |
|----------|-----------|------------|---------------------------|
| analyst  | ✅         | ❌          | ✅                          |
| reviewer | ❌         | ✅          | ✅                          |
| admin    | ✅         | ✅          | ✅                          |

## Login Fix Notes

Demo credentials are now force-verified on every backend startup, so old database passwords will not break login anymore.

- Analyst: `analyst@netzeroiq.com` / `analyst@1234`
- Reviewer: `reviewer@netzeroiq.com` / `reviewer@1234`
- Admin: `dinesh@netzeroiq.com` / `dinesh@1234`

The frontend also skips token refresh on `/auth/login`, so stale localStorage tokens cannot interfere with login.

---

# 📄 License

MIT License © 2026 netzeroiq Inc.

---

# ⭐ Support

If you found this project valuable, give it a ⭐ on GitHub.
