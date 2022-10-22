package io.github.vantoozz.kli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.dikt.dikt
import io.github.vantoozz.dikt.put
import io.github.vantoozz.kli.commands.AbstractCommand
import io.github.vantoozz.kli.config.Config
import io.github.vantoozz.kli.config.yamlConfigLoader


inline fun <reified T : Config> kli(
    crossinline containerBuilder: MutableContainer.() -> Unit,
    vararg commands: AbstractCommand<*>,
) = kli(
    yamlConfigLoader<T>(),
    containerBuilder,
    *commands
)

inline fun <reified T : Config> kli(
    crossinline configBuilder: (String?) -> T,
    crossinline containerBuilder: MutableContainer.() -> Unit,
    vararg commands: AbstractCommand<*>,
) = object : CliktCommand() {
    override fun run() = Unit
}.apply {

    commands.forEach {
        it.setContainer { environment ->
            configBuilder(environment).let { config ->
                dikt {
                    put(config)
                    put<Config>(config)
                    containerBuilder()
                }
            }
        }
    }

    subcommands(*commands)
}
