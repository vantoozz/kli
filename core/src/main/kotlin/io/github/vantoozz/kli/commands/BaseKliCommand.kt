package io.github.vantoozz.kli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.dikt.dikt

class BaseKliCommand(
    name: String? = null,
    private val containerBuilder: MutableContainer.(String?) -> Unit,
) : CliktCommand(
    name = name,
) {
    private val environment
            by option(
                "-e", "--env",
                envvar = "KLI_ENVIRONMENT",
                help = "App environment"
            )

    override fun run() {
        currentContext.obj = { dikt { containerBuilder(environment) } }
    }
}
