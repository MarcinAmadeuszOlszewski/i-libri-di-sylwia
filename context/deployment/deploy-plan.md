# Deployment Plan

## Goal

Deliver the first deployment of the current Java 21 / Spring Boot 4.0.6 app to Railway, using `@context/foundation/infrastructure.md` as the deployment source of truth and `@context/foundation/tech-stack.md` for runtime and build constraints.

## Chosen Release Mode

- First release: manual Railway CLI deployment.
- GitHub Actions automation: deferred until after the first live release succeeds.
- Smoke checks: reuse the existing `/` and `/v1/work` endpoints.

## Current Repo State

- `@src/main/resources/application.properties` does not yet bind the app to Railway's injected `PORT`.
- No `.github/workflows/`, Railway-specific config, `Dockerfile`, or `Procfile` exists yet.
- `@context/foundation/tech-stack.md` still contains the older `deployment_target: aws-ecs` hint, which conflicts with the recorded Railway decision.
- The app already exposes the minimal surface needed for a first release smoke check through `/` and `/v1/work`.

## Planned Work

1. **Prepare Railway runtime**
   - Add `server.port=${PORT:8080}` in `@src/main/resources/application.properties`.
   - Keep the current Spring Boot startup path unchanged unless Railway runtime detection forces extra configuration.

2. **Prepare the manual Railway deployment path**
   - Start with Railway's default Java/Spring Boot detection path.
   - Add only minimal Railway-specific scaffolding if the default path is not enough.
   - Keep a Docker fallback as a contingency, not as the default first-deploy path.

3. **Validate locally before deployment**
   - Run `mvnw.cmd test`.
   - Run `mvnw.cmd clean verify`.

4. **Execute the first deployment**
   - Authenticate with Railway CLI.
   - Initialize or link the Railway project.
   - Set environment variables only if the app needs any beyond Railway defaults.
   - Deploy with Railway CLI.

5. **Verify the deployed app**
   - Inspect runtime logs from Railway.
   - Smoke-check `/`.
   - Smoke-check `/v1/work`.

6. **Reconcile repository metadata**
   - Update `@context/foundation/tech-stack.md` so it no longer points to AWS ECS as the active deployment target for this repository.
   - Leave GitHub Actions explicitly out of scope for this first release.

## Risks and Watch Items

- `Spring Boot 4.0.6` is newer than many generic platform examples, so Railway behavior should be validated against the exact pinned version.
- If Railway's default Java build path fails, the next step is a Docker fallback rather than piling on ad hoc workarounds.
- If persistence is added later, application rollback will not roll back database state automatically.
- The working tree already contains uncommitted changes in `@.github/copilot-instructions.md` and `@context/foundation/infrastructure.md`; deployment work must not overwrite them.

## Expected Deliverables

- Railway-ready application runtime configuration.
- Minimal deployment scaffolding only if Railway auto-detection requires it.
- First manual Railway deployment.
- Public smoke-checked URL.
- Synchronized deployment metadata in `@context/foundation/tech-stack.md`.

## Out of Scope

- GitHub Actions deployment automation.
- Docker-first deployment unless Railway auto-detection fails.
- Production-scale infrastructure concerns such as multi-region, HA, or DR.
