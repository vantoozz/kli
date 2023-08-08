plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

object V {
    const val kli = "0.1.0"
    const val logback = "1.4.6"
    const val slf4j = "2.0.5"
}

dependencies {
    implementation("org.slf4j:slf4j-api:${V.slf4j}")
    implementation("ch.qos.logback:logback-core:${V.logback}")
    implementation("ch.qos.logback:logback-classic:${V.logback}")

    implementation("io.github.vantoozz.kli:runner:${V.kli}")
}

application {
    mainClass.set("io.github.vantoozz.kli.examples.AppKt")
}

tasks {
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
