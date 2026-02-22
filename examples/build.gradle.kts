import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_3

plugins {
    kotlin("jvm") version "2.3.10"
    application
}

repositories {
    mavenCentral()
}

object V {
    const val kli = "2.1.1"
}

dependencies {
    implementation("io.github.vantoozz.kli:runner:${V.kli}")
}

application {
    mainClass.set("io.github.vantoozz.kli.examples.AppKt")
}

kotlin {
    compilerOptions {
        apiVersion.set(KOTLIN_2_3)
        jvmTarget.set(JvmTarget.JVM_21)
    }

    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
