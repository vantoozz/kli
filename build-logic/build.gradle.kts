plugins {
    `kotlin-dsl`
    id("com.github.ben-manes.versions") version "0.51.0"
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.github.ben-manes:gradle-versions-plugin:0.51.0")
    implementation("fr.brouillard.oss.gradle:gradle-jgitver-plugin:0.9.1")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.5")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")
    implementation("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.4")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}
