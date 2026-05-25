---
starter_id: spring
package_manager: maven
project_name: i-libri-di-sylwia
hints:
  language_family: java
  team_size: solo
  deployment_target: railway
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

## Why this stack

This is a small, after-hours web MVP with a five-week timeline and no stack-forcing requirements like payments, realtime, AI, or background jobs. Spring Boot is the recommended Java default for web apps and fits a product that benefits from explicit types, strong conventions, and mature documentation. Verified scaffolding support makes it the safest choice, while Railway matches the current MVP deployment decision and keeps the first release simple through a manual CLI flow before any GitHub Actions automation is introduced. Guest access and manual progress saving stay in scope without forcing a more specialized starter.
