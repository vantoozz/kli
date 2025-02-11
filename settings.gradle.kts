pluginManagement {
    includeBuild("build-logic")
}

plugins {
    id("com.gradle.develocity") version ("3.19.1")
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.9.0")
}

rootProject.name = "kli"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "core",
    "config",
    "runner",
)

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
    }
}
