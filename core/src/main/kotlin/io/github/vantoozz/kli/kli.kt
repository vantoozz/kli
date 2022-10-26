package io.github.vantoozz.kli

import com.github.ajalt.clikt.core.subcommands
import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.kli.commands.BaseKliCommand
import io.github.vantoozz.kli.commands.KliCommand

fun kli(
    command: KliCommand<*>,
) = kli(setOf(command))

fun kli(
    commands: Collection<KliCommand<*>>,
) = kli({}, commands)

fun kli(
    containerBuilder: MutableContainer.(String?) -> Unit,
    command: KliCommand<*>,
) = kli(containerBuilder, setOf(command))

fun kli(
    containerBuilder: MutableContainer.(String?) -> Unit,
    commands: Collection<KliCommand<*>>,
) = BaseKliCommand(containerBuilder)
    .apply {
        subcommands(commands)
    }
