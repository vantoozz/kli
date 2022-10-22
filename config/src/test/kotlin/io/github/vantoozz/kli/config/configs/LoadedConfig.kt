package io.github.vantoozz.kli.config.configs

import io.github.vantoozz.kli.config.Config

internal data class LoadedConfig(
    val stringParam: String,
    val structParam: StructParam,
) : Config {

    internal data class StructParam(
        val someString: String,
        val someInt: Int,
        val someBoolean: Boolean,
    )
}
