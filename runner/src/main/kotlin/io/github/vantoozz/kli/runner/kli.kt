package io.github.vantoozz.kli.runner

import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.kli.config.KliConfig
import io.github.vantoozz.kli.config.kli as configKli

inline fun <reified T : KliConfig> kli(
    crossinline containerBuilder: MutableContainer.() -> Unit,
    args: Array<String>,
    commands: CommandsListBuilder.() -> Unit,
) = configKli<T>(
    containerBuilder,
    *CommandsListBuilder().apply(commands).build()
).main(args)
