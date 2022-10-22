package io.github.vantoozz.kli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.option
import io.github.vantoozz.dikt.Container
import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.dikt.dikt
import io.github.vantoozz.kli.commands.KliCommand

fun kli(vararg commands: KliCommand<*>) =
    kli({}, *commands)

fun kli(
    containerBuilder: MutableContainer.(String?) -> Unit,
    vararg commands: KliCommand<*>,
) = object : CliktCommand() {

    private val environment
            by option(
                "-e", "--env",
                envvar = "KLI_ENVIRONMENT",
                help = "App environment"
            )

    override fun run() {
        val function: () -> Container = { dikt { containerBuilder(environment) } }
        currentContext.obj = function
    }

}.apply {
    subcommands(*commands)
}
