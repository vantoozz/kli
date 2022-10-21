plugins {
    id("io.github.vantoozz.kli.kotlin-library-conventions")
}

dependencies {
    api(project(":core"))
    implementation("com.sksamuel.hoplite:hoplite-core:${V.hoplite}")
}
