plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.github.ben-manes:gradle-versions-plugin:0.46.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
    implementation("org.jetbrains.kotlinx:kover:0.6.1")
}
