# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

KLI is a CLI-applications framework for Kotlin, published to Maven Central under `io.github.vantoozz.kli`. It wraps [Clikt](https://github.com/ajalt/clikt) for CLI parsing, uses [Dikt](https://github.com/vantoozz/dikt) for dependency injection, and [Hoplite](https://github.com/sksamuel/hoplite) for YAML configuration loading.

## Development Commands

```bash
# Build and test
./gradlew build                    # Build + run tests + quality checks
./gradlew test                     # All unit tests
./gradlew :core:test               # Single module tests
./gradlew clean                    # Clean build artifacts

# Run a single test class
./gradlew :core:test --tests 'HandlingCommandTest'

# Run a single test method
./gradlew :core:test --tests 'HandlingCommandTest.it creates handler'

# Code quality
./gradlew detekt                   # Kotlin static analysis
./gradlew koverVerify              # Coverage verification (100% on core)

# Dependency management
./gradlew dependencyUpdates        # Check for dependency updates
```

## Architecture

### Module Structure

Module dependency chain: `runner` → `config` → `core`.

- **`core`** — Core framework. Defines `KliCommand<T>`, `SimpleCommand`, `BaseKliCommand`, and the `kli()` entry-point functions. Dependencies are resolved from a Dikt container, passed through Clikt's context.
- **`config`** — Adds YAML configuration loading (via Hoplite). Extends core's `kli()` with config-aware overloads that load `KliConfig` implementations from `/config.yaml` and optional `/config.{environment}.yaml`.
- **`runner`** — Convenience layer on top of `config`. Provides a DSL-style `kli<Config>(container, args) { +Command() }` entry point using `CommandsListBuilder`.
- **`examples`** — Example application (not published). Consumes the framework from Maven Central. Has its own `build.gradle.kts` with a separate `object V` for version pinning.
- **`build-logic`** — Gradle convention plugins and shared dependency versions (`versions.kt`).

### Command Pattern

Each command extends `KliCommand<T>` where `T` is a handler class. The handler's constructor parameters are resolved from the Dikt DI container. This separates CLI parsing (Clikt) from business logic (handler).

```kotlin
class CallService : KliCommand<CallService.Handler>() {
    override val handler = Handler::class
    override fun handle(handler: Handler) { handler.run() }

    class Handler(private val service: Service) {
        fun run() { service.doSomething() }
    }
}
```

For commands with no dependencies, extend `SimpleCommand` instead.

### Container Lifecycle

`BaseKliCommand` creates an `AutoClosableContainer` per command invocation. The container builder receives the environment string (from `--env`/`KLI_ENVIRONMENT`). Sub-commands retrieve the container builder via Clikt's `requireObject`.

### Dependency Versions

Managed centrally in `build-logic/src/main/kotlin/versions.kt` as `object V`. No version numbers in module `build.gradle.kts` files — all modules reference `V.xxx` constants.

## Technology Stack

**Core:** Kotlin 2.3, JVM target 21, Adoptium JDK, Clikt, Dikt, Hoplite

**Build:** Gradle with convention plugins in `build-logic/` (`kotlin-common-conventions`, `kotlin-library-conventions`), jgitver for versioning (MAVEN strategy, git tags)

**Testing/Quality:** JUnit Platform, kotlin.test, Detekt, Kover (100% coverage on `core`)

**Publishing:** Maven Central via NMCP aggregation (`publishAggregationToCentralPortal`)

## Testing Standards

### Test Class Visibility

**REQUIRED:** Test classes use `internal` modifier.

```kotlin
internal class SomeTest {
    @Test
    fun `it does something`() {
        // ...
    }
}
```

### Test Imports

**CRITICAL: Kotlin tests MUST use `kotlin.test.*` for assertions, NOT JUnit Jupiter assertions.**

```kotlin
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertNull
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import kotlin.test.assertIs
import kotlin.test.assertContains
import kotlin.test.assertFailsWith
```

Prefer `assertFailsWith` from `kotlin.test` over `assertThrows` from `org.junit.jupiter.api`.

### AAA Pattern

**REQUIRED:** All tests follow **AAA (Arrange-Act-Assert)** pattern with three distinct phases separated by blank lines.

**CRITICAL: NO AAA COMMENTS** - Never add `// Arrange`, `// Act`, `// Assert` comments. The pattern must be evident from structure alone.

```kotlin
@Test
fun `it creates handler`() {
    val mock = LoggerMock()

    kli(
        { bind<Logger>(mock) },
        LogSomething(),
    ).parse(listOf("log-something"))

    assertTrue { mock.logged("some string") }
}
```

### Test Naming

**Convention:** `it <action> <condition>` with backticks.

```kotlin
@Test fun `it closes dependencies`() {}
@Test fun `it creates handler`() {}
@Test fun `it does nothing if no handler`() {}
@Test fun `it handles environment variable`() {}
```

**Vocabulary:** creates, returns, builds, throws, closes, binds, validates, rejects, converts, parses, configures, handles, registers, loads, prints, adds, runs, does not

### Assertions

**CRITICAL: Avoid meaningless assertions.**

```kotlin
// WRONG - Always passes, tests nothing
assertTrue(true)
assertFalse(false)
assertEquals(value, value)

// WRONG - Constructor always returns non-null in Kotlin
val instance = SomeClass()
assertNotNull(instance)
```

**Use `assertDoesNotThrow` when verifying operations complete without exceptions:**

```kotlin
assertDoesNotThrow { SomeClass() }
```

**When to use each assertion:**

- `assertDoesNotThrow`: Operation completes without exceptions
- `assertEquals/assertTrue/assertContains`: Verify actual behavior and output values
- `assertNull`: When null is the expected outcome
- `assertSame`: For identity checks (singleton, Unit)
- `assertIs`: For polymorphic type verification
- `assertThrows`/`assertFailsWith`: For expected exceptions

### Parameterized Tests

Use `@ParameterizedTest` when multiple inputs share the same assertion logic instead of copy-pasting tests with minor variations.

Available sources: `@EnumSource`, `@ValueSource`, `@MethodSource`, `@CsvSource`

## Code Standards

1. **NEVER use the not-null assertion operator `!!`** in production code. Use safe alternatives (`?.`, `?:`, `let`, `require()`, `checkNotNull()`)
2. **NO unnecessary comments** — code must be self-documenting. Comments only for KDoc on public APIs or complex business logic (WHY, not WHAT)
3. Prefer immutable data classes and sealed interfaces for data modeling
4. Prefer single-expression functions where they improve readability
5. Use read-only collections (`listOf`, `mapOf`, `setOf`) by default; use mutable variants only when mutation is required and scope them as narrowly as possible
6. Use `use {}` for `AutoCloseable` resource management
7. Mark incomplete code with `TODO("reason")` rather than empty stubs or placeholder comments
8. Commit messages follow [Conventional Commits](https://www.conventionalcommits.org/) (`feat`, `fix`, `refactor`, `test`, `docs`, `chore`, etc.)

## Quality Requirements

- 100% line coverage on `core` module (enforced by Kover); `config` and `runner` have kover disabled
- Zero Detekt violations
- All tests must have at least one assertion
