package io.github.vantoozz.kli.runner

import io.github.vantoozz.dikt.MutableContainer
import io.github.vantoozz.kli.commands.KliCommand
import io.github.vantoozz.kli.config.Konfig
import io.github.vantoozz.kli.config.kli as configKli

inline fun <reified T : Konfig> kli(
    crossinline containerBuilder: MutableContainer.() -> Unit,
    args: Array<String>,
    commands: CommandsListBuilder.() -> Unit,
) = configKli<T>(
    containerBuilder,
    *CommandsListBuilder().apply(commands).build()
).main(args)

class CommandsListBuilder {

    private val commands: MutableList<KliCommand<*>> = mutableListOf()
    fun add(command: KliCommand<*>) {
        commands.add(command)
    }

    fun build() = commands.toTypedArray()
}
