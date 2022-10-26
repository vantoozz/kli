package io.github.vantoozz.kli.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import io.github.vantoozz.dikt.Container
import kotlin.reflect.KClass

abstract class KliCommand<T : Any> : CliktCommand() {

    protected abstract val handler: KClass<T>

    private val containerBuilder by requireObject<() -> Container>()

    override fun run() =
        containerBuilder()[handler]?.let {
            handle(it)
        } ?: Unit

    protected abstract fun handle(handler: T)

}
