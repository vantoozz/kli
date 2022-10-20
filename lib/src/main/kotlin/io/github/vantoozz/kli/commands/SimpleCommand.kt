package io.github.vantoozz.kli.commands

abstract class SimpleCommand : AbstractCommand<Unit>() {

    override val handler = Unit::class

    override fun handle(handler: Unit) = handle()

    abstract fun handle()
}
