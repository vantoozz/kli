package io.github.vantoozz.kli.examples

import io.github.vantoozz.kli.config.KliConfig

internal data class Config(
    val service: Service,
) : KliConfig {
    internal data class Service(
        val hostname: String,
        val port: Int,
    )
}
