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
        kotlinOptions.jvmTarget = "1.8"
    }
}

kotlin {
    jvmToolchain {
        languageVersion
            .set(JavaLanguageVersion.of(8))
    }
}
