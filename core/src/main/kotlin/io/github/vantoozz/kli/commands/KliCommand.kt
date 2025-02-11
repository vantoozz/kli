package io.github.vantoozz.kli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import io.github.vantoozz.dikt.AutoClosableContainer
import kotlin.reflect.KClass

abstract class KliCommand<T : Any> : CliktCommand() {

    protected abstract val handler: KClass<T>

    private val containerBuilder by requireObject<() -> AutoClosableContainer>()

    override fun run() {
        containerBuilder().use { container ->
            container[handler]?.let {
                handle(it)
            }
        }
    }

    protected abstract fun handle(handler: T)

}
