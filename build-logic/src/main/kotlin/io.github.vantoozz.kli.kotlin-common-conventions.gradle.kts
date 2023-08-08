import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    kotlin("jvm")
    id("com.github.ben-manes.versions")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:${V.junit}")
}

tasks {
    test {
        useJUnitPlatform()
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        !isStable(candidate.version)
    }
}

fun isStable(version: String): Boolean {
    if (setOf("RELEASE", "FINAL", "GA")
            .any { version.uppercase().contains(it) }
    ) {
        return true
    }

    return "^[0-9,.v-]+(-r)?$".toRegex().matches(version)
}
