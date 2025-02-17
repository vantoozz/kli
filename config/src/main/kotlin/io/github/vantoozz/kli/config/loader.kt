package io.github.vantoozz.kli.config

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.ExperimentalHoplite

@OptIn(ExperimentalHoplite::class)
inline fun <reified T : KliConfig> yamlConfigLoader(): (String?) -> T = { environment ->
    T::class.takeIf {
        it == KliConfig::class
    }?.let {
        object : KliConfig {} as T
    } ?: ConfigLoaderBuilder.default()
        .withExplicitSealedTypes()
        .build()
        .loadConfigOrThrow(
            listOfNotNull(
                environment?.let { "/config.$environment.yaml" },
                "/config.yaml"
            )
        )
}
