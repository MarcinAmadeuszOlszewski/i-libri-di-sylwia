---
bootstrapped_at: 2026-05-20T17:53:06Z
starter_id: spring
starter_name: Spring Boot
project_name: i-libri-di-sylwia
language_family: java
package_manager: maven
cwd_strategy: subdir-then-move
bootstrapper_confidence: verified
phase_3_status: ok
audit_command: null
---

## Hand-off

```yaml
---
starter_id: spring
package_manager: maven
project_name: i-libri-di-sylwia
hints:
  language_family: java
  team_size: solo
  deployment_target: aws-ecs
  ci_provider: github-actions
  ci_default_flow: auto-deploy-on-merge
  bootstrapper_confidence: verified
  path_taken: standard
  quality_override: false
  self_check_answers: null
  has_auth: false
  has_payments: false
  has_realtime: false
  has_ai: false
  has_background_jobs: false
---
```

## Why this stack

This is a small, after-hours web MVP with a five-week timeline and no stack-forcing requirements like payments, realtime, AI, or background jobs. Spring Boot is the recommended Java default for web apps and fits a product that benefits from explicit types, strong conventions, and mature documentation. Verified scaffolding support makes it the safest choice, while AWS ECS matches your deployment target and GitHub Actions with auto-deploy keeps delivery straightforward. Guest access and manual progress saving stay in scope without forcing a more specialized starter.

## Pre-scaffold verification

| Signal      | Value   | Severity | Notes |
| ----------- | ------- | -------- | ----- |
| npm package | not run | not run  | non-JS starter |
| GitHub repo | not run | not run  | `docs_url` is not a GitHub repository URL |

## Scaffold log

**Resolved invocation**: `cd .bootstrap-scaffold; curl.exe -fsSL "https://start.spring.io/starter.tgz?dependencies=web,devtools&type=maven-project&javaVersion=21&groupId=com.example&artifactId=i-libri-di-sylwia" -o starter.tgz; tar -xzf starter.tgz`  
**Strategy**: subdir-then-move  
**Exit code**: 0  
**Files moved**: 10  
**Conflicts (.scaffold siblings)**: none  
**.gitignore handling**: append-merged  
**.bootstrap-scaffold cleanup**: deleted

## Post-scaffold audit

**Tool**: skipped -- no built-in audit tool for java  
**Recommended external tool**: OWASP Dependency-Check or Snyk

## Hints recorded but not acted on

| Hint | Value |
| ---- | ----- |
| bootstrapper_confidence | verified |
| quality_override | false |
| path_taken | standard |
| self_check_answers | null |
| team_size | solo |
| deployment_target | aws-ecs |
| ci_provider | github-actions |
| ci_default_flow | auto-deploy-on-merge |
| has_auth | false |
| has_payments | false |
| has_realtime | false |
| has_ai | false |
| has_background_jobs | false |

## Next steps

Next: a future skill will set up agent context (CLAUDE.md, AGENTS.md). For now, your project is scaffolded and verified -- happy hacking.

Useful manual steps in the meantime:
- `git init` (if you have not already) to start your own repo history.
- Review any `.scaffold` siblings the conflict policy created and decide which version of each file to keep.
- Address audit findings per your project's risk tolerance -- the full breakdown is in this log.
