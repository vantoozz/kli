plugins {
    id("io.github.vantoozz.kli.kotlin-common-conventions")
    id("org.jetbrains.kotlinx.kover")
    `java-library`
    `maven-publish`
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("kli") {
            from(components["java"])
            groupId = "io.github.vantoozz.kli"
            version = "0.0.1"
//            afterEvaluate {
//                artifactId = tasks.jar.get().archiveBaseName.get()
//            }

            pom {
                name.set("KLI")
                description.set("CLI-applications framework for Kotlin")
                url.set("https://github.com/vantoozz/kli")
                licenses {
                    license {
                        name.set("The MIT License (MIT)")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("vantoozz")
                        name.set("Ivan Nikitin")
                        email.set("vantoozz@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/vantoozz/kli.git")
                    developerConnection.set("scm:git:https://github.com/vantoozz/kli.git")
                    url.set("https://github.com/vantoozz/kli")
                }
            }
        }
    }
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
