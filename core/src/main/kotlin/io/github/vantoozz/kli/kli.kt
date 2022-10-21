package io.github.vantoozz.kli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import io.github.vantoozz.dikt.Container
import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.dikt.dikt
import io.github.vantoozz.kli.commands.AbstractCommand

typealias ContainerBuilder = (String?) -> Container

fun kli(vararg commands: AbstractCommand<*>) =
    kli({}, *commands)

fun kli(
    containerBuilder: MutableContainer.(String?) -> Unit,
    vararg commands: AbstractCommand<*>,
) = object : CliktCommand() {
    override fun run() = Unit
}.apply {

    commands.forEach {
        it.setContainer { dikt { containerBuilder(it) } }
    }

    subcommands(*commands)
}
