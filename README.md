# Multi-Tenant SaaS Billing Engine

Spring Boot backend for a multi-tenant billing infrastructure.

## Tech Stack
- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- Testcontainers
- Actuator
- OpenAPI (Swagger)

## Current Project Structure

```text
src/main/java/com/company/project
+-- config
+-- controller
+-- service
+-- repository
+-- entity
+-- dto
+-- mapper
+-- exception
+-- security
+-- util
+-- ProjectApplication.java
```

## Configuration Profiles
- `application.yml`
- `application-dev.yml`
- `application-prod.yml`
- `application-test.yml`

Use env vars for DB credentials:
- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`

## API Docs & Health
- Swagger UI: `/swagger-ui.html`
- OpenAPI JSON: `/v3/api-docs`
- App health: `/api/v1/health`
- Actuator health: `/actuator/health`

## Tenant APIs (v1)
- `POST /api/v1/tenants`
- `GET /api/v1/tenants`
- `GET /api/v1/tenants/{tenantId}`
- `PUT /api/v1/tenants/{tenantId}`
- `DELETE /api/v1/tenants/{tenantId}`

## Plan APIs (v1)
- `POST /api/v1/plans`
- `GET /api/v1/plans?tenantId={tenantId}`
- `GET /api/v1/plans/{planId}`
- `PUT /api/v1/plans/{planId}`
- `DELETE /api/v1/plans/{planId}`

## Subscription APIs (v1)
- `POST /api/v1/subscriptions`
- `GET /api/v1/subscriptions?tenantId={tenantId}`
- `GET /api/v1/subscriptions/{subscriptionId}`
- `PUT /api/v1/subscriptions/{subscriptionId}/status`
- `POST /api/v1/subscriptions/{subscriptionId}/cancel`

## Branch + PR Strategy (Step-by-step)
1. `main` stays stable.
2. Create one branch per feature area.
3. Keep commits small and meaningful.
4. Open PR for each branch.

Planned branch order:
1. `chore/project-bootstrap`
2. `feat/tenant-management`
3. `feat/plan-and-subscription-core`
4. `feat/invoice-and-payment-foundation`
5. `chore/dockerization`
6. `chore/github-actions-cicd`
7. `feat/observability-and-hardening`

## Run Locally
```bash
mvn spring-boot:run
```
