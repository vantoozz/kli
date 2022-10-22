package io.github.vantoozz.kli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.dikt.dikt
import io.github.vantoozz.kli.commands.KliCommand

fun kli(vararg commands: KliCommand<*>) =
    kli({}, *commands)

fun kli(
    containerBuilder: MutableContainer.(String?) -> Unit,
    vararg commands: KliCommand<*>,
) = object : CliktCommand() {
    override fun run() = Unit
}.apply {

    commands.forEach {
        it.setContainer { dikt { containerBuilder(it) } }
    }

    subcommands(*commands)
}
