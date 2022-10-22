package io.github.vantoozz.kli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import io.github.vantoozz.dikt.Container
import kotlin.reflect.KClass

abstract class KliCommand<T : Any> : CliktCommand() {

    protected abstract val handler: KClass<T>

    private lateinit var containerBuilder: (String?) -> Container

    fun setContainer(containerBuilder: (String?) -> Container) {
        if (!this::containerBuilder.isInitialized) {
            this.containerBuilder = containerBuilder
        }
    }

    private val environment
            by option(
                "-e", "--env",
                envvar = "KLI_ENVIRONMENT",
                help = "App environment"
            )

    override fun run() =
        containerBuilder(environment)[handler]?.let {
            handle(it)
        } ?: Unit

    protected abstract fun handle(handler: T)

}
