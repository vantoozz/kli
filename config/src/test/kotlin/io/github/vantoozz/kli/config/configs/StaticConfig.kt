package io.github.vantoozz.kli.config.configs

import io.github.vantoozz.kli.config.Config

internal data class StaticConfig(
    val environment: String,
) : Config
