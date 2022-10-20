package io.github.vantoozz.kli.commands

import io.github.vantoozz.kli.mocks.Logger

internal class LogSomething : AbstractCommand<LogSomething.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        handler.doSomething()
    }

    internal class Handler(
        private val logger: Logger,
    ) {
        fun doSomething() {
            logger.log("some string")
        }
    }
}
