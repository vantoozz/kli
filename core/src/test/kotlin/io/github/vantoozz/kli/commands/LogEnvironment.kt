package io.github.vantoozz.kli.commands

import io.github.vantoozz.kli.mocks.Logger

internal class LogEnvironment: AbstractCommand<LogEnvironment.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        handler.run()
    }

    internal class Handler(
        private val logger: Logger,
        private val environment: Environment,
    ) {
        fun run() {
            logger.log(environment.value)
        }
    }

    data class Environment(
        val value: String,
    )
}
