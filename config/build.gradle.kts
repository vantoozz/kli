plugins {
    id("io.github.vantoozz.kli.kotlin-library-conventions")
}

dependencies {
    api(project(":core"))

    implementation("com.sksamuel.hoplite:hoplite-core:${V.hoplite}")
    implementation("com.sksamuel.hoplite:hoplite-yaml:${V.hoplite}")
}

publishing.publications.withType(MavenPublication::class).all {
    pom {
        name.set("KLI Config")
        description.set("Config library for KLI")
    }
}
