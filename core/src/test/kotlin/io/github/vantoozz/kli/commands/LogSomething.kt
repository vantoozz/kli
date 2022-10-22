package io.github.vantoozz.kli.commands

import io.github.vantoozz.kli.mocks.Logger

internal class LogSomething : KliCommand<LogSomething.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        handler.run()
    }

    internal class Handler(
        private val logger: Logger,
    ) {
        fun run() {
            logger.log("some string")
        }
    }
}
