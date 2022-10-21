package io.github.vantoozz.kli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import io.github.vantoozz.dikt.Container
import io.github.vantoozz.dikt.dikt
import io.github.vantoozz.kli.commands.AbstractCommand

typealias ContainerBuilder = (String?) -> Container

fun kli(vararg commands: AbstractCommand<*>) =
    kli(null, *commands)

fun kli(
    containerBuilder: ContainerBuilder? = null,
    vararg commands: AbstractCommand<*>,
) = object : CliktCommand() {
    override fun run() = Unit
}.apply {

    commands.forEach {
        it.setContainer(containerBuilder ?: { dikt { } })
    }

    subcommands(*commands)
}
