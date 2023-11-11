import fr.brouillard.oss.jgitver.Strategies
import kotlinx.kover.gradle.plugin.dsl.MetricType

plugins {
    `java-library`
    `maven-publish`
    id("fr.brouillard.oss.gradle.jgitver")
    id("io.github.vantoozz.kli.kotlin-common-conventions")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.kotlinx.kover")
    signing
}

java {
    withSourcesJar()
    withJavadocJar()
}

jgitver {
    strategy = Strategies.MAVEN
    useDirty = true
}

publishing {
    publications {
        create<MavenPublication>("kli") {
            from(components["java"])
            groupId = "io.github.vantoozz.kli"

            versionMapping {
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }

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

        repositories {
            maven {
                name = "Sonatype"
                afterEvaluate {
                    url = if (project.version.toString().endsWith("-SNAPSHOT")
                        || project.version.toString().endsWith("-dirty")
                    ) {
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
        }
    }
}

signing {
    sign(publishing.publications)
}

koverReport {
    verify {
        MetricType.values().forEach {
            rule("Minimal ${it.name} coverage rate in percents") {
                bound {
                    metric = it
                    minValue = 100
                }
            }
        }
    }
}
