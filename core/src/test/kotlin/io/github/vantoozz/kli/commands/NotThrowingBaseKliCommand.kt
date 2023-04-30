package io.github.vantoozz.kli.commands

import com.github.ajalt.clikt.core.CliktCommand
import io.github.vantoozz.dikt.dikt

internal class NotThrowingBaseKliCommand : CliktCommand() {
    override fun run() {
        currentContext.obj = { dikt(null) { } }
    }
}
