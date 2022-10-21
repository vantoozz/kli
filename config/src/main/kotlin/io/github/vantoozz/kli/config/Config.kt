package io.github.vantoozz.kli.config

import com.sksamuel.hoplite.ConfigLoader

internal data class Config(
    val someService: SomeService,
) {
    companion object Loader {
        fun load(environment: Environment) = ConfigLoader()
            .loadConfigOrThrow<Config>(
                listOf(
                    "/config.${environment.name.lowercase()}.yaml",
                    "/config.yaml"
                )
            )
    }

    internal data class SomeService(
        val hostname: String,
        val port: Int,
        val index: String,
    )
}
