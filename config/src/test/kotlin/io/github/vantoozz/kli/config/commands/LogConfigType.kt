package io.github.vantoozz.kli.config.commands

import io.github.vantoozz.kli.commands.KliCommand
import io.github.vantoozz.kli.config.Config
import io.github.vantoozz.kli.config.Logger

internal class LogConfigType : KliCommand<LogConfigType.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        handler.run()
    }

    internal class Handler(
        private val config: Config,
        private val logger: Logger,
    ) {
        fun run() {
            logger.log(config::class.supertypes[0].toString())
        }
    }
}
