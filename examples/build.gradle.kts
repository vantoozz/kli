import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_1

plugins {
    `kotlin-dsl`
    application
}

repositories {
    mavenCentral()
}

object V {
    const val kli = "1.0.0"
}

dependencies {
    implementation("io.github.vantoozz.kli:runner:${V.kli}")
}

application {
    mainClass.set("io.github.vantoozz.kli.examples.AppKt")
}

kotlin {
    compilerOptions {
        apiVersion.set(KOTLIN_2_1)
        jvmTarget.set(JvmTarget.JVM_21)
    }

    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
