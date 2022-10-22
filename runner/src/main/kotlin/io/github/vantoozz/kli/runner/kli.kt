package io.github.vantoozz.kli.runner

import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.kli.commands.KliCommand
import io.github.vantoozz.kli.config.Konfig
import io.github.vantoozz.kli.config.kli as configKli

inline fun <reified T : Konfig> kli(
    crossinline containerBuilder: MutableContainer.() -> Unit,
    args: Array<String>,
    vararg commands: KliCommand<*>,
) = configKli<T>(containerBuilder, *commands).main(args)
