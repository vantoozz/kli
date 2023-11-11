plugins {
    `kotlin-dsl`
    id("com.github.ben-manes.versions") version "0.49.0"
}

repositories {
    gradlePluginPortal()
}

val kotlinVersion = "1.9.20"

dependencies {
    implementation("com.github.ben-manes:gradle-versions-plugin:0.49.0")
    implementation("fr.brouillard.oss.gradle:gradle-jgitver-plugin:0.9.1")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.4")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}
