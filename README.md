# KLI

A CLI-applications framework for Kotlin. Combines [Clikt](https://github.com/ajalt/clikt) for argument parsing, [Dikt](https://github.com/vantoozz/dikt) for dependency injection, and [Hoplite](https://github.com/sksamuel/hoplite) for YAML configuration loading.

Published to [Maven Central](https://central.sonatype.com/namespace/io.github.vantoozz.kli) under `io.github.vantoozz.kli`.

## Installation

Requires JVM 21+.

Pick the module that fits your needs:

```kotlin
// build.gradle.kts
dependencies {
    // Full DSL with config loading and command registration
    implementation("io.github.vantoozz.kli:runner:2.1.1")

    // Config loading only (includes core)
    implementation("io.github.vantoozz.kli:config:2.1.1")

    // Core only — commands + DI, no config loading
    implementation("io.github.vantoozz.kli:core:2.1.1")
}
```

Each higher-level module transitively includes the ones below it: `runner` -> `config` -> `core`.

## Quick start

```kotlin
fun main(args: Array<String>) =
    kli<Config>(container, args) {
        +DescribeService()
        +CallService()
    }
```

This registers two subcommands, wires up a DI container, loads YAML config, and runs the CLI. The sections below explain each piece.

## Commands

### Commands with dependencies

Extend `KliCommand<T>` where `T` is a handler class whose constructor parameters are resolved from the DI container. This separates CLI parsing from business logic.

```kotlin
class CallService : KliCommand<CallService.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        handler.run()
    }

    class Handler(private val service: Service) {
        fun run() {
            service.doSomething()
        }
    }
}
```

### Simple commands

For commands that don't need injected dependencies, extend `SimpleCommand`:

```kotlin
class Greet : SimpleCommand() {
    override fun handle() {
        echo("Hello!")
    }
}
```

Since `KliCommand` extends Clikt's `CliktCommand`, you have full access to Clikt's options, arguments, and subcommands.

## Dependency injection

Dependencies are configured through a container builder function that receives a `MutableContainer` from Dikt:

```kotlin
val container: MutableContainer.() -> Unit = {
    using(Config::class) { config ->
        put<Service> {
            DummyService(config.service.hostname, config.service.port)
        }
    }
}
```

The container is created fresh per command invocation and automatically closed afterwards (`AutoClosableContainer`), so any `AutoCloseable` dependencies are cleaned up.

## Configuration

The `config` module loads YAML configuration from classpath resources via Hoplite.

Define a config class implementing `KliConfig`:

```kotlin
data class Config(
    val service: Service,
) : KliConfig {
    data class Service(
        val hostname: String,
        val port: Int,
    )
}
```

Place config files on the classpath (e.g. `src/main/resources/`):

```
src/main/resources/
  config.yaml                  # base config (always loaded)
  config.staging.yaml          # environment override (optional)
  config.production.yaml       # environment override (optional)
```

```yaml
# config.yaml
service:
  hostname: localhost
  port: 2345
```

```yaml
# config.production.yaml — only override what differs
service:
  hostname: service.production.domain
```

The environment is selected with `--env` / `-e` or the `KLI_ENVIRONMENT` environment variable:

```bash
./my-app --env production describe-service
# or
KLI_ENVIRONMENT=production ./my-app describe-service
```

Environment-specific files are merged on top of the base `config.yaml`.

## Modules

| Module   | Artifact                            | Description                                     |
|----------|-------------------------------------|-------------------------------------------------|
| `core`   | `io.github.vantoozz.kli:core`      | Commands, DI integration (Clikt + Dikt)         |
| `config` | `io.github.vantoozz.kli:config`    | YAML config loading (Hoplite), extends core     |
| `runner` | `io.github.vantoozz.kli:runner`    | DSL entry point with command registration       |

## Example

The [`examples/`](examples/) directory contains a standalone project that demonstrates the full framework: config loading, dependency injection, and command registration.

It defines two commands — `describe-service` (prints config values) and `call-service` (uses an injected service) — wired together with a YAML config and a DI container.

To run it:

```bash
cd examples
./gradlew run --args="describe-service"
# Hostname: localhost, port: 2345

./gradlew run --args="--env production describe-service"
# Hostname: service.production.domain, port: 2345

./gradlew run --args="call-service"
# Connecting to localhost:2345
```

## License

[MIT](LICENSE)
