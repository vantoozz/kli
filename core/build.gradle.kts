plugins {
    id("io.github.vantoozz.kli.kotlin-library-conventions")
}

dependencies {
    api("com.github.ajalt.clikt:clikt:${V.clikt}")
    api("io.github.vantoozz:dikt:${V.dikt}")
}

publishing.publications.withType(MavenPublication::class).all {
    pom {
        name.set("KLI")
        description.set("CLI-applications framework for Kotlin")
    }
}

kover {
    reports {
        filters {
            excludes {
                classes(
                    "*\$inlined\$requireObject\$default*"
                )
            }
        }
    }
}
