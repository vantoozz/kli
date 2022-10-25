package io.github.vantoozz.kli

import com.github.ajalt.clikt.core.subcommands
import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.kli.commands.BaseKliCommand
import io.github.vantoozz.kli.commands.KliCommand

fun kli(vararg commands: KliCommand<*>) =
    kli({}, *commands)

fun kli(
    containerBuilder: MutableContainer.(String?) -> Unit,
    vararg commands: KliCommand<*>,
) = kli(containerBuilder, name = null, *commands)

fun kli(
    containerBuilder: MutableContainer.(String?) -> Unit,
    name: String?,
    vararg commands: KliCommand<*>,
) = BaseKliCommand(
    name?.takeUnless { it.isBlank() },
    containerBuilder
).apply {
    subcommands(*commands)
}
