plugins {
    `kotlin-dsl`
    id("com.github.ben-manes.versions") version "0.47.0"
}

repositories {
    gradlePluginPortal()
}

val kotlinVersion = "1.9.0"

dependencies {
    implementation("com.github.ben-manes:gradle-versions-plugin:0.47.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kover:0.6.1")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}
