---
project: "I Libri Di Sylwia"
context_type: greenfield
created: 2026-05-18
updated: 2026-05-18
product_type: web-app
target_scale:
  users: small
  qps: low
  data_volume: small
timeline_budget:
  mvp_weeks: 5
  hard_deadline: 2026-08-10
  after_hours_only: true
checkpoint:
  current_phase: 8
  phases_completed: [1, 2, 3, 4, 5, 6, 7]
  gray_areas_resolved:
    - topic: pain category
      decision: workflow friction + missing capability + coordination overhead
    - topic: primary persona scope
      decision: Polish-speaking self-learner of Italian, looking for alternatives to textbook-based study
    - topic: market insight
      decision: Existing language-learning products skew toward English and other larger markets, leaving Italian-focused reading workflows underserved
    - topic: auth strategy
      decision: guest mode for MVP; no account required during first validation
    - topic: access model
      decision: no role separation in MVP; a guest can use the core flow without signing in
    - topic: gated routes
      decision: no gated routes in MVP because the core flow starts in guest mode
    - topic: MVP timeline
      decision: 5-week MVP accepted as a sustained after-hours effort
    - topic: scope discipline
      decision: keep the book list, selection flow, inline translation, and manual continue-later in the first version; defer full auth and automatic persistence
    - topic: MVP success signal
      decision: inline translation must make reading feel easier than switching to external tools
    - topic: FR priority
      decision: only the revised core reading capabilities remain in Functional Requirements; quality constraints move to NFRs
    - topic: primary user story
      decision: the core story combines guest access, book reading, inline translation, and manual continue-later
    - topic: reader layout
      decision: preserve sentence-level interaction without requiring a rigid sentence-by-sentence layout
    - topic: business logic rule
      decision: reveal only the meaning the reader asks for, in context, instead of translating whole texts upfront
    - topic: translation performance bar
      decision: word translations should usually appear within 300–500 ms and sentence translations within about 1 second
    - topic: browser support
      decision: support modern desktop and mobile browsers
    - topic: product type
      decision: web app
    - topic: target scale
      decision: first release targets a single user or a small handful of users
    - topic: hard deadline
      decision: 2026-08-10
    - topic: work mode
      decision: after-hours only
    - topic: non-goals
      decision: exclude flashcards, user-uploaded books, audio, multi-language support, and adaptive learning logic from MVP
  frs_drafted: 7
  quality_check_status: accepted
---

## Vision & Problem Statement

Polish-speaking adults who are learning Italian through native books lose momentum the moment they need help with a word or sentence. Their reading flow breaks because they have to jump to an external dictionary or translator and manually retype or paste text.

The opportunity exists because tools in this space are concentrated around English and other larger language markets. That leaves self-directed Italian learners with a weaker reading-based workflow than the one available to learners in more popular languages.

At much larger scale, this rule would likely evolve from simple click-to-translate behavior into a context-aware explanation system that balances learning value, latency, and cost.

## User & Persona

### Primary persona

A Polish-speaking person who is learning Italian on their own and actively looking for alternatives to textbook-driven study. They reach for this product while reading an Italian text and wanting to understand a word or sentence without leaving the reading experience.

## Success Criteria

### Primary

- A first-time visitor can open the app in guest mode, choose a book, read it with sentence-level interaction, translate words and full sentences inline, and manually save where to continue later.

### Secondary

- Some users regularly use inline word translation while reading and report that it feels more comfortable and less frustrating than switching to external tools.

### Guardrails

- Any saved reading progress or interaction data must be protected, and translation integrations must not expose unnecessary identifying data.
- Inline translations must feel fast and reliable, and failures must degrade gracefully instead of breaking the reading experience.

## Timeline acknowledgment

Acknowledged on 2026-05-18: 5-week MVP requires sustained dedication; user accepted.

## User Stories

### US-01: Read with inline translation and manual continuation

- **Given** a visitor who has selected a book from the available list
- **When** the user opens the book and reads the text, clicking on words or sentences to see their translations
- **Then** the application displays the requested translations and lets the user save where to continue later

#### Acceptance Criteria

- The selected book opens in the reading view without requiring external tools or account creation
- Word and sentence translations are shown in the reading experience
- The reader can mark a continuation point and later return to it

## Functional Requirements

### Authentication

- FR-001: User can enter the product in guest mode without creating an account. Priority: must-have
  > Socrates: Counter-argument considered: self-serve account creation adds auth scope before the reading value is proven. Resolution: changed from account auth to guest mode for first validation.

### Library & Progress

- FR-002: User can view a list of available books or texts. Priority: must-have
  > Socrates: Counter-argument considered: a single starter text could validate the experience faster than building a library screen. Resolution: kept; a small selectable library remains part of the MVP.
- FR-003: User can select a book to start reading. Priority: must-have
  > Socrates: Counter-argument considered: one fixed text could remove the selection step. Resolution: kept; selecting from available texts is still part of the MVP.
- FR-004: User can manually save a place in a book and continue from that point later. Priority: must-have
  > Socrates: Counter-argument considered: automatic progress persistence adds backend complexity before the core translation loop is proven. Resolution: changed from automatic saving and auto-resume to manual continue-later.

### Reading & Translation

- FR-005: User can read text in a translation-friendly format that supports sentence-level interaction. Priority: must-have
  > Socrates: Counter-argument considered: strict sentence-by-sentence layout may over-constrain the reading format. Resolution: kept sentence-level interaction but relaxed the rigid layout requirement.
- FR-006: User can click a word to see its translation. Priority: must-have
  > Socrates: Counter-argument considered: word translation without enough context could mislead users. Resolution: kept; direct word lookup remains core to the reading workflow.
- FR-007: User can click a sentence to see its translation. Priority: must-have
  > Socrates: Counter-argument considered: full-sentence translation may add cost and latency. Resolution: kept; sentence translation remains part of the core value proposition.

## Non-Functional Requirements

- Word translations should usually appear within about 300-500 ms, and sentence translations within about 1 second, so reading flow is not broken.
- The app should work reliably on modern desktop and mobile browsers.
- If a translation request fails, the reading UI must stay usable and show a clear fallback state.
- Translation requests and any saved interaction data must not expose unnecessary identifying data to third parties.

## Business Logic

The app lets users read foreign-language text and selectively reveal meaning on demand by translating individual words or sentences in context, instead of requiring full-text translation upfront.

The rule consumes user-facing reading actions: choosing a text, reading it, and clicking on a word or sentence they do not understand.

It produces a contextual translation of only the selected element and returns it in a lightweight way that preserves the surrounding reading flow.

The user experiences this rule directly inside the reader: they read normally, click on unfamiliar text, and receive just enough meaning to continue without leaving the page or translating larger chunks unnecessarily.

## Access Control

- Authentication: guest mode for MVP; no account is required during first validation.
- Roles: no role separation in MVP.
- Route handling: the core reader flow is available in guest mode, so there are no gated routes in the first version.

## Non-Goals

- No flashcards or vocabulary-training features in MVP, so the first version stays focused on reading with inline translation.
- No user-uploaded books or EPUB import in MVP, so legal and ingestion complexity does not block validation.
- No audio or pronunciation features in MVP, so the first version stays centered on text-based reading support.
- No multi-language support beyond the Italian-reading use case, so the product tests one learner workflow clearly.
- No adaptive or personalized learning logic in MVP, so the first release avoids building stateful teaching behavior before the core reading loop is proven.

## Open Questions

1. **No open questions at this stage.** — The current shape session closed with the soft gate accepted.

## Quality cross-check

- Access Control: present.
- Business Logic: present.
- Project artifacts: present.
- Timeline-cost ack: present.
- Non-Goals: present.
- Preserved behavior: n/a (greenfield).
