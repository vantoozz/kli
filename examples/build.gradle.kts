plugins {
    id("io.github.vantoozz.kli.kotlin-common-conventions")
    application
}

dependencies {
    implementation(project(":runner"))

    implementation("org.slf4j:slf4j-api:${V.slf4j}")
    implementation("ch.qos.logback:logback-core:${V.logback}")
    implementation("ch.qos.logback:logback-classic:${V.logback}")
}
