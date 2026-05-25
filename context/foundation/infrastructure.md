---
project: i-libri-di-sylwia
researched_at: 2026-05-25T12:56:54+02:00
recommended_platform: Railway
runner_up: Render
context_type: mvp
tech_stack:
  language: Java
  framework: Spring Boot 4.0.6
  runtime: JVM (Java 21)
---

## Recommendation

**Deploy on Railway.**

For this Java 21 / Spring Boot MVC MVP, Railway is the best balance of stack fit and operational simplicity. It runs a persistent JVM process without forcing a Docker-first workflow, matches the interview constraints (request/response only, DX over absolute cheapest cost, single-region acceptable, external providers acceptable), and keeps agent-driven operations scriptable through CLI, readable docs, and a usable integration story. The earlier `deployment_target: aws-ecs` hint in `@context/foundation/tech-stack.md` is still a valid later-stage option, but for MVP speed Railway is the better decision contract.

## Platform Comparison

Hard-filtered before final ranking:
- **Vercel**: no JVM runtime or container path for Spring Boot.
- **Netlify**: no JVM runtime for functions; rollback and log workflows are weaker from the CLI.
- **Cloudflare Workers + Pages**: strong platform on paper, but the core Workers/Pages runtime does not run Spring Boot; the Containers workaround adds a JS gateway, unpublished container pricing, and extra cold-start risk.

Scoring key: Pass = 1, Partial = 0.5, Fail = 0.

| Platform | CLI-first | Managed/Serverless | Agent-readable docs | Stable deploy API | MCP / Integration | Total |
|---|---|---|---|---|---|---|
| Railway | Pass | Pass | Pass | Pass | Partial | 4.5 / 5 |
| Render | Pass | Partial | Pass | Pass | Pass | 4.5 / 5 |
| Fly.io | Pass | Partial | Partial | Pass | Fail | 3.0 / 5 |
| Cloudflare Workers + Pages | Pass | Pass | Pass | Pass | Pass | Filtered |
| Vercel | Pass | Pass | Pass | Pass | Partial | Filtered |
| Netlify | Partial | Pass | Partial | Partial | Pass | Filtered |

**Railway:** Railway earned the top spot because it supports Java 21 / Spring Boot directly through Railpack, keeps the deploy loop short (`railway up`, `railway logs`, `railway redeploy`), publishes `llms.txt` and markdown-friendly docs, and fits the MVP's single-region, fast-iteration profile. The only score drag is MCP maturity: the Railway MCP server exists, but Railway still describes it as work in progress, so CLI/API remains the safer operational backbone.

**Render:** Render is the strongest runner-up because its CLI, hosted MCP server, markdown docs, rollback support, and managed Postgres story are all solid. It lost the recommendation because Java is a Docker-only path and the practical price floor for a comfortable JVM web service is materially higher than Railway, which hurts the DX-first MVP calculus.

**Fly.io:** Fly.io remains viable, especially if later you want more control over regions and process topology. It scored lower because Java is a manual Docker path, docs are GitHub-readable but not as agent-friendly as Railway or Render, there is no official MCP story, and the managed database path is noticeably pricier for a small MVP.

**Cloudflare Workers + Pages:** Cloudflare has excellent CLI, docs, and agent-oriented tooling, but it was filtered out by runtime fit. A Spring Boot app would need Cloudflare Containers plus a JS Worker gateway rather than running as a straightforward JVM app, which is extra surface area for a request/response MVP that does not need edge compute.

**Vercel:** Vercel is polished and agent-friendly for the runtimes it supports, but Java / Spring Boot is not one of them. Because this project is not being rewritten into a supported serverless runtime, Vercel is not shortlistable.

**Netlify:** Netlify has a strong MCP story and a clean JAMstack workflow, but its compute layer is the wrong shape for this app. No JVM runtime means Spring Boot and Thymeleaf are dead on arrival, and key operational loops such as rollback and log tailing still lean more on the dashboard than the terminal.

### Shortlisted Platforms

#### 1. Railway (Recommended)

Railway won because it gives this repository the cleanest path from Maven project to running URL. The stack is already Java 21 and Spring Boot 4.0.6, the app is request/response only, and the interview answers favored faster iteration over squeezing every dollar. Railway matches that profile with native JVM support, persistent processes, simple logs, lightweight preview environments, and a low enough starting cost that the team can defer infrastructure complexity until the product proves itself.

#### 2. Render

Render came second because it is operationally strong and its MCP and markdown-doc story are arguably better than Railway's today. It lost on two MVP-specific gaps: Java requires a Dockerfile from day one, and the realistic cost floor for a comfortable JVM service is higher. If the team decides it values a more explicit container workflow over Railpack convenience, Render is the safest swap.

#### 3. Fly.io

Fly.io came third because it can absolutely run the app well, especially if multi-region or more VM-style control later becomes important. For this MVP, though, it asks for more manual setup, has a thinner agent integration story, and turns databases into a more expensive decision sooner than Railway or Render.

## Anti-Bias Cross-Check: Railway

### Devil's Advocate - Weaknesses

1. Railway looks simple until state appears: app deploy is easy, but the moment you add a database, backups, migration rollback, and restore drills become your problem if you use Railway-managed templates.
2. The agent story is good but not fully mature: Railway MCP is still labeled work in progress, so critical unattended operations should not depend on it.
3. Rollback is weaker than the happy-path marketing suggests: code rollback is easy, but schema migrations and data changes do not roll back with the service image.
4. Railpack convenience can become opacity: if auto-build or auto-start detection stops fitting the project, you can fall off the easy path into custom Docker or manual JVM tuning quickly.
5. Railway is ideal for a single-region MVP, but if this app later needs stricter compliance, network controls, or multi-region behavior, migration pressure can appear sooner than expected.

### Pre-Mortem - How This Could Fail

Six months later, the team regrets choosing Railway because they treated easy deploy as easy operations. The first release shipped fast, which masked the fact that the real risk surface was state, not compute. A database was added, a few environment-specific variables accumulated, and the team kept relying on Railway's smooth deploy experience instead of tightening its migration and restore discipline. Then a release introduced a schema change that worked in tests but broke real traffic. Rolling back the app image was simple, but the old data shape was gone. At the same time, the Spring Boot app had grown enough that the original cheap sizing assumptions no longer held, so performance and cost both drifted in the wrong direction. Because the platform had abstracted the happy path so well, the team had not practiced the less glamorous paths: backup restore, rollback across code plus data, and environment drift control. The platform did not fail them; their assumption that MVP convenience removed operational responsibility did.

### Unknown Unknowns

- **Rollback boundary:** Railway can redeploy old app versions, but DB migrations, seeded content, and destructive data edits remain unless you have a separate restore path.
- **Memory headroom:** a tiny Spring Boot MVC app fits comfortably; a slightly larger one with templates, caches, and observability can outgrow cheap MVP sizing faster than expected.
- **Environment sprawl:** preview, staging, and production variables can drift unless ownership and promotion rules are defined early.
- **Buildpack escape hatch:** once you need custom OS packages, strict JVM tuning, or nonstandard startup behavior, Railpack may stop being enough and Docker becomes the fallback.
- **Version skew:** this repo pins Spring Boot 4.0.6, which is newer than many platform examples; validate Railpack behavior against the exact pinned version instead of assuming any generic Spring Boot tutorial matches it.
- **MCP maturity:** Railway's agent tooling is promising, but platform automation should still have a documented CLI/API fallback.

## Operational Story

How the chosen platform operates day to day for this project:

- **Preview deploys**: with a linked GitHub repo and PR environments enabled, each pull request gets an isolated temporary environment and shareable URL; Railway deletes that environment when the PR is merged or closed, so treat previews as disposable and keep them off production data.
- **Secrets**: store env vars in Railway Variables at the service or shared-project level; set and rotate them with `railway variables set KEY=value`, scope them to the target environment, and assume project members with the right Railway access can manage them.
- **Rollback**: use Railway's deployment history in the dashboard to republish a prior deployment, or use `railway redeploy` to rerun the last good deploy path; application rollback is fast, but DB migrations and data fixes must be reversed separately.
- **Approval**: a human should approve production deploys, primary secret rotation, DB create/drop operations, and destructive migrations; an agent may handle preview deploys, read-only inspection, log review, and non-destructive variable updates.
- **Logs**: read runtime output with `railway logs`, stream with `railway logs --follow`, narrow scope with `railway logs --service <service>`, and inspect a past release with `railway logs --deployment <deployment-id>`.

## Risk Register

| Risk | Source | Likelihood | Impact | Mitigation |
|---|---|---|---|---|
| App rollback does not undo DB schema or data changes | Devil's advocate / Unknown unknowns | M | H | Use versioned migrations, require backward-compatible releases where possible, and rehearse backup/restore before production data matters. |
| Railway-managed DB templates add ops ownership | Research finding / Devil's advocate | M | M | Prefer an external managed DB if you want cleaner operational boundaries, or explicitly assign backup and restore ownership if DB stays on Railway. |
| JVM memory and cost creep as the app grows | Pre-Mortem / Unknown unknowns | M | M | Baseline memory under light load early, watch RSS after each feature wave, and budget for plan growth before OOM pressure appears. |
| Railpack auto-detection stops fitting the project | Devil's advocate / Unknown unknowns | M | M | Keep a Dockerfile fallback documented so the team can leave the buildpack path without rethinking the whole platform decision. |
| Single-region MVP choice becomes a later constraint | Devil's advocate / Pre-Mortem | L | M | Front the app with CDN/static caching where possible, keep data/storage choices portable, and re-evaluate before any global rollout. |
| MCP surface changes while still maturing | Research finding / Unknown unknowns | M | L | Treat CLI and API as the source of truth, and use MCP as a convenience layer rather than the only operations path. |
| Spring Boot 4.0.6 behaves differently from common platform examples | Unknown unknowns / Research finding | M | M | Validate the first Railway deploy early on the exact pinned version, keep Java 21 explicit, and switch to Docker if Railpack assumptions diverge. |

## Getting Started

1. Add `server.port=${PORT:8080}` to `@src/main/resources/application.properties`; the current file only sets `spring.application.name`, and Railway needs the app to bind to its injected port.
2. Run `mvnw.cmd test` locally on Java 21 and confirm the Spring Boot 4.0.6 build resolves cleanly before involving the platform.
3. Install the Railway CLI with `npm install -g @railway/cli`, run `railway login`, then `railway init` or link the GitHub repo if you want PR environments.
4. Set required env vars with `railway variables set KEY=value`, then deploy with `railway up` and confirm the boot logs plus generated public URL.
5. Decide now whether persistence will live on Railway or an external managed provider; if it stays on Railway, define backup ownership and migration rollback rules before the first real users arrive.

## Out of Scope

The following were not evaluated in this research:
- Docker image configuration
- CI/CD pipeline setup
- Production-scale architecture (multi-region, HA, DR)
