package io.github.vantoozz.kli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.NullableOption
import com.github.ajalt.clikt.parameters.options.RawOption
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import io.github.vantoozz.kli.ContainerBuilder
import io.github.vantoozz.kli.config.Environment
import kotlin.reflect.KClass

abstract class AbstractCommand<T : Any> : CliktCommand() {

    protected abstract val handler: KClass<T>

    private lateinit var containerBuilder: ContainerBuilder

    fun setContainer(containerBuilder: ContainerBuilder) {
        if (!this::containerBuilder.isInitialized) {
            this.containerBuilder = containerBuilder
        }
    }

    private val environment
            by option("-e", "--env", help = "App environment")
                .env<Environment>()
                .default(Environment.LOCAL)

    override fun run() =
        containerBuilder(environment)[handler]?.let {
            handle(it)
        } ?: Unit

    protected abstract fun handle(handler: T)

}

inline fun <reified T : Enum<T>> RawOption.env(
    ignoreCase: Boolean = true,
): NullableOption<T, T> = choice(
    enumValues<T>().associateBy { it.name },
    ignoreCase = ignoreCase
)
