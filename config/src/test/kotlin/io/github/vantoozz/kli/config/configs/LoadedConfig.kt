package io.github.vantoozz.kli.config.configs

import io.github.vantoozz.kli.config.Konfig

internal data class LoadedConfig(
    val stringParam: String,
    val structParam: StructParam,
) : Konfig {

    internal data class StructParam(
        val someString: String,
        val someInt: Int,
        val someBoolean: Boolean,
    )
}
