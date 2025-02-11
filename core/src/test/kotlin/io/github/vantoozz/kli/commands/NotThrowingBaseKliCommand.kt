package io.github.vantoozz.kli.commands

import com.github.ajalt.clikt.core.CliktCommand
import io.github.vantoozz.dikt.Options
import io.github.vantoozz.dikt.diktAutoCloseable

internal class NotThrowingBaseKliCommand : CliktCommand() {
    override fun run() {
        currentContext.obj = {
            diktAutoCloseable(
                setOf(Options.WITHOUT_EXCEPTION_ON_FAILURE)
            ) {}
        }
    }
}
