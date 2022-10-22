package io.github.vantoozz.kli.examples

import io.github.vantoozz.kli.config.Konfig

internal data class Config(
    val service: Service,
) : Konfig {
    internal data class Service(
        val hostname: String,
        val port: Int,
    )
}
