val ossrhUsername: String by project
val ossrhPassword: String by project

plugins {
    `java-library`
    `maven-publish`
    id("io.github.vantoozz.kli.kotlin-common-conventions")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.kotlinx.kover")
    signing
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        repositories {
            maven {
                name = "Sonatype"
                url = if (version.toString().endsWith("SNAPSHOT")) {
                    uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                } else {
                    uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                }

                credentials {
                    username = project.properties["ossrhUsername"] as String
                    password = project.properties["ossrhPassword"] as String
                }
            }
        }
        create<MavenPublication>("kli") {
            from(components["java"])
            groupId = "io.github.vantoozz.kli"
            version = "0.0.2"

            pom {
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

signing {
    sign(publishing.publications)
}

kover {
    verify {
        kotlinx.kover.api.CounterType.values().forEach {
            rule {
                name = "Minimal ${it.name} coverage rate in percents"
                bound {
                    counter = it
                    minValue = 100
                }
            }
        }
    }
}
