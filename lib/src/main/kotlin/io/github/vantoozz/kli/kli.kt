package io.github.vantoozz.kli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import io.github.vantoozz.dikt.Container
import io.github.vantoozz.dikt.dikt
import io.github.vantoozz.kli.commands.AbstractCommand
import io.github.vantoozz.kli.config.Environment

typealias ContainerBuilder = (Environment) -> Container

fun kli(vararg commands: AbstractCommand<*>) =
    kli(null, *commands)

fun kli(
    containerBuilder: ContainerBuilder?,
    vararg commands: AbstractCommand<*>,
) =  kli(containerBuilder, setOf(), *commands)

fun kli(
    containerBuilder: ContainerBuilder? = null,
    environments: Set<String> = setOf(),
    vararg commands: AbstractCommand<*>,
) = object : CliktCommand() {
    override fun run() = Unit
}.apply {

    commands.forEach {
        it.setContainer(containerBuilder ?: { dikt { } })
    }

    subcommands(*commands)
}
