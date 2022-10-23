package io.github.vantoozz.kli.config.configs

import io.github.vantoozz.kli.config.KliConfig

internal data class StaticConfig(
    val environment: String,
) : KliConfig
