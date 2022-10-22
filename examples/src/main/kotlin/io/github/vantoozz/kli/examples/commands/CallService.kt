package io.github.vantoozz.kli.examples.commands

import io.github.vantoozz.kli.commands.KliCommand
import io.github.vantoozz.kli.examples.service.Service

internal class CallService : KliCommand<CallService.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        handler.run()
    }

    internal class Handler(
        private val service: Service,
    ) {
        fun run() {
            service.doSomething()
        }
    }
}
