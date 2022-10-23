package io.github.vantoozz.kli

import com.github.ajalt.clikt.core.subcommands
import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.kli.commands.KliCommand
import io.github.vantoozz.kli.commands.BaseKliCommand

fun kli(vararg commands: KliCommand<*>) =
    kli({}, *commands)

fun kli(
    containerBuilder: MutableContainer.(String?) -> Unit,
    vararg commands: KliCommand<*>,
) = BaseKliCommand(containerBuilder)
    .apply {
        subcommands(*commands)
    }
