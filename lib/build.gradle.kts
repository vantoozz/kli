plugins {
    kotlin("jvm") version "1.7.20"
    `java-library`
}

object V {
    const val clikt = "3.5.0"
    const val dikt = "0.9.3-SNAPSHOT"
    const val hoplite = "2.6.2"
    const val junit = "5.9.1"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("bom"))
    implementation("com.sksamuel.hoplite:hoplite-core:${V.hoplite}")
    implementation("com.github.ajalt.clikt:clikt:${V.clikt}")
    implementation("io.github.vantoozz:dikt:${V.dikt}")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:${V.junit}")
    testImplementation("com.sksamuel.hoplite:hoplite-yaml:${V.hoplite}")
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
        languageVersion
            .set(JavaLanguageVersion.of(11))
    }
}
