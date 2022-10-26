plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("org.jetbrains.kotlinx:kover:0.6.1")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.21.0")
}
