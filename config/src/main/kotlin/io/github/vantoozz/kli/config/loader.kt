package io.github.vantoozz.kli.config

import com.sksamuel.hoplite.ConfigLoader

inline fun <reified T : KliConfig> yamlConfigLoader(): (String?) -> T = { environment ->
    T::class.takeIf {
        it == KliConfig::class
    }?.let {
        object : KliConfig {} as T
    } ?: ConfigLoader().loadConfigOrThrow(
        listOfNotNull(
            environment?.let { "/config.$environment.yaml" },
            "/config.yaml"
        )
    )
}
