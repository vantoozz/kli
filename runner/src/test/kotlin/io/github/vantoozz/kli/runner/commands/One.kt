package io.github.vantoozz.kli.runner.commands

import io.github.vantoozz.kli.commands.KliCommand
import io.github.vantoozz.kli.runner.service.Service

internal class One : KliCommand<One.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        repeat(5) {
            handler.run()
        }
    }

    internal class Handler(
        private val service: Service,
    ) {
        fun run() {
            service.call()
        }
    }
}
