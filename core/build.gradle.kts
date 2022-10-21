plugins {
    id("io.github.vantoozz.kli.kotlin-library-conventions")
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:${V.clikt}")
    implementation("io.github.vantoozz:dikt:${V.dikt}")
}
