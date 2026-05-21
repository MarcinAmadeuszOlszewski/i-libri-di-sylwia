# Copilot Instructions

## Build and test commands

Use the Maven wrapper. On Windows run `mvnw.cmd`; on macOS/Linux run `./mvnw`.

| Task | Command |
| --- | --- |
| Run the app locally | `./mvnw spring-boot:run` |
| Full build | `./mvnw clean verify` |
| Run all tests | `./mvnw test` |
| Run one test class | `./mvnw -Dtest=ILibriDiSylwiaApplicationTests test` |
| Run one test method | `./mvnw -Dtest=ILibriDiSylwiaApplicationTests#contextLoads test` |

No dedicated lint or formatter task is configured in `pom.xml` yet.

## Coding standards

### Variables and types

- In production code, declare variables with explicit types and make them `final` when possible. Do not use `var` or `val`.
- In test classes, always use `val` (Lombok) for local variable declarations instead of explicit types or `var`. Example: `val result = service.process(input);`

### Test method naming and documentation

- Test method names must not contain underscores; use camelCase instead.
- Use `@DisplayName` on all test methods to provide a detailed, human-readable test description.

### Class annotation ordering

- Order class-level annotations as follows: Spring annotations first, Lombok annotations second, all other annotations last. Within each group, apply alphabetical order.
- Example order: `@Component` → `@Configuration` → `@Data` → `@RequiredArgsConstructor` → `@Deprecated` → `@Nullable`

## Failure modes

### Inconsistent local typing between production and tests

- **Impact:** Mixing `val` and explicit local types in the wrong places makes reviews noisy and causes avoidable rewrite churn when code is aligned back to project conventions.
- **Rule that prevents it:** Follow the existing variables-and-types rule: explicit `final`-leaning types in production code, `val` for local variables in test classes.

### Misordered annotations on Spring or Lombok beans

- **Impact:** Putting Lombok or miscellaneous annotations ahead of Spring annotations breaks the expected scan pattern for class declarations and makes bean definitions harder to verify quickly.
- **Rule that prevents it:** Follow the existing class annotation ordering rule: Spring annotations first, Lombok second, other annotations last, with alphabetical order inside each group.

### Test names written_with_underscores

- **Impact:** Underscore-based test names drift from the repository convention and create inconsistent test output alongside methods that rely on camelCase plus `@DisplayName` for readability.
- **Rule that prevents it:** Follow the existing test naming rule: test methods must use camelCase without underscores and include `@DisplayName`.

## High-level architecture

- The codebase is still at scaffold stage. `src/main/java/com/example/i_libri_di_sylwia/ILibriDiSylwiaApplication.java` is the only application class and boots a single Spring Boot app rooted at `com.example.i_libri_di_sylwia`.
- Product behavior is defined primarily in `@context/foundation/prd.md`, not in Java code yet. Treat that file as the source of truth for MVP scope: guest access, book selection, inline word and sentence translation, and manual continue-later progress.
- Stack and delivery decisions live in `@context/foundation/tech-stack.md` and `@context/changes/bootstrap-verification/verification.md`: Java 21, Spring Boot, Maven wrapper, GitHub Actions, and AWS ECS.
- Runtime configuration is currently minimal and lives in `@src/main/resources/application.properties`.
- The current test suite is a single `@SpringBootTest` smoke test in `src/test/java/com/example/i_libri_di_sylwia/ILibriDiSylwiaApplicationTests.java`, so new feature work will also establish the first real application slices and testing patterns.

## Key conventions

- Keep all production and test code under the `com.example.i_libri_di_sylwia` package tree. The Maven artifact uses hyphens, but the Java package must keep underscores; `@HELP.md` documents this conversion.
- Keep new Spring-managed classes under the root package so component scanning from `ILibriDiSylwiaApplication` continues to work without extra scan configuration.
- Check `@context/foundation/prd.md` before adding new endpoints, services, or models. The current MVP is explicitly guest-first and excludes auth flows, uploads, flashcards, adaptive learning, and automatic progress saving.
- When product or stack assumptions change, update the existing files in `context/foundation/` in place rather than creating dated copies. `@context/foundation/README.md` defines that workflow.
