package io.github.vantoozz.kli.config

import com.sksamuel.hoplite.ConfigLoader

inline fun <reified T : Konfig> yamlConfigLoader(): (String?) -> T = {
    ConfigLoader().loadConfigOrThrow(
        listOfNotNull(
            it?.let { "/config.$it.yaml" },
            "/config.yaml"
        )
    )
}
