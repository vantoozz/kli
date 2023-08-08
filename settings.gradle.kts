pluginManagement {
    includeBuild("build-logic")
}

plugins {
    id("com.gradle.enterprise") version ("3.11.2")
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.6.0")
}

rootProject.name = "kli"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "core",
    "config",
    "runner",
)

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}
