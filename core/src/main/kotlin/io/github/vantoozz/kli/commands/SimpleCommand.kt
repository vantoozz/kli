package io.github.vantoozz.kli.commands

abstract class SimpleCommand : KliCommand<Unit>() {

    override val handler = Unit::class

    override fun handle(handler: Unit) = handle()

    abstract fun handle()
}
