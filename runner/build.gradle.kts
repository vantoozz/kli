plugins {
    id("io.github.vantoozz.kli.kotlin-library-conventions")
}

dependencies {
    api(project(":config"))
}

publishing.publications.withType(MavenPublication::class).all {
    pom {
        name.set("KLI Runner")
        description.set("Runner library for KLI")
    }
}
