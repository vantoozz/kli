package io.github.vantoozz.kli.config

import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.dikt.put
import io.github.vantoozz.kli.commands.KliCommand
import io.github.vantoozz.kli.kli as coreKli

inline fun <reified T : KliConfig> kli(
    crossinline containerBuilder: MutableContainer.() -> Unit,
    vararg commands: KliCommand<*>,
) = kli(
    yamlConfigLoader<T>(),
    containerBuilder,
    *commands
)

inline fun <reified T : KliConfig> kli(
    crossinline configBuilder: (String?) -> T,
    crossinline containerBuilder: MutableContainer.() -> Unit,
    vararg commands: KliCommand<*>,
) = coreKli(
    {
        configBuilder(it).let { config ->
            put(config)
            put<KliConfig>(config)
        }

        containerBuilder()
    },
    *commands
)
