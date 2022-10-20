package io.github.vantoozz.kli.commands

internal class SimpleHello : SimpleCommand() {
    override fun handle() {
        echo("Hello")
    }
}
