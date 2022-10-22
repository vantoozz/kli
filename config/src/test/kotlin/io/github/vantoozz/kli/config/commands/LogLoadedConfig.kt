package io.github.vantoozz.kli.config.commands

import io.github.vantoozz.kli.commands.KliCommand
import io.github.vantoozz.kli.config.Logger
import io.github.vantoozz.kli.config.configs.LoadedConfig

internal class LogLoadedConfig : KliCommand<LogLoadedConfig.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        handler.run()
    }

    internal class Handler(
        private val config: LoadedConfig,
        private val logger: Logger,
    ) {
        fun run() {
            logger.log(config.structParam.someString)
        }
    }
}
