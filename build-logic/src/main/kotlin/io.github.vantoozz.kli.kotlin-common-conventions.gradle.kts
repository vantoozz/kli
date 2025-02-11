import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_1

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
}

kotlin {
    compilerOptions {
        apiVersion.set(KOTLIN_2_1)
        jvmTarget.set(JvmTarget.JVM_21)
    }

    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
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
