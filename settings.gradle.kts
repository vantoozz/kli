pluginManagement {
    includeBuild("build-logic")
}

plugins {
    id("com.gradle.develocity") version "3.19.2"
    id("com.gradleup.nmcp.settings") version "1.4.4"
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
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

fun credentialProperty(name: String): String {
    providers.gradleProperty(name).orNull?.takeIf { it.isNotBlank() }?.let { return it }
    System.getenv("ORG_GRADLE_PROJECT_$name")?.takeIf { it.isNotBlank() }?.let { return it }

    val globalProperties = java.util.Properties()
    file("${System.getProperty("user.home")}/.gradle/gradle.properties")
        .takeIf { it.exists() }
        ?.inputStream()
        ?.use { globalProperties.load(it) }

    return globalProperties.getProperty(name) ?: ""
}

nmcpSettings {
    centralPortal {
        username = credentialProperty("mavenCentralUsername")
        password = credentialProperty("mavenCentralPassword")
        publishingType = "AUTOMATIC"
    }
}
