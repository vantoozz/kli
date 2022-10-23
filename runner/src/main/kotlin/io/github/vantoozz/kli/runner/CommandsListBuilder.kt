package io.github.vantoozz.kli.runner

import io.github.vantoozz.kli.commands.KliCommand

class CommandsListBuilder {

    private val commands: MutableList<KliCommand<*>> = mutableListOf()

    fun add(command: KliCommand<*>) {
        commands.add(command)
    }

    fun build() = commands.toTypedArray()

    operator fun KliCommand<*>.unaryPlus() {
        commands.add(this)
    }
}
