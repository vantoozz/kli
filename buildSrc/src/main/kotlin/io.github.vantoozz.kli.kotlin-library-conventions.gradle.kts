plugins {
    id("io.github.vantoozz.kli.kotlin-common-conventions")
    id("org.jetbrains.kotlinx.kover")
    `java-library`
}

kover {
    verify {
        rule {
            name = "Minimal line coverage rate in percents"
            bound {
                minValue = 100
            }
        }
    }
}
