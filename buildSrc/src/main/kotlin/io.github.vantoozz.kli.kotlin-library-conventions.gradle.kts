val ossrhUsername: String by project
val ossrhPassword: String by project

plugins {
    id("io.github.vantoozz.kli.kotlin-common-conventions")
    id("org.jetbrains.kotlinx.kover")
    `java-library`
    `maven-publish`
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
            version = "0.0.1"

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
        rule {
            name = "Minimal line coverage rate in percents"
            bound {
                minValue = 100
            }
        }
    }
}
