package io.github.vantoozz.kli.config.commands

import io.github.vantoozz.kli.commands.AbstractCommand
import io.github.vantoozz.kli.config.Logger
import io.github.vantoozz.kli.config.configs.LoadedConfig
import io.github.vantoozz.kli.config.configs.StaticConfig

internal class LogLoadedConfig : AbstractCommand<LogLoadedConfig.Handler>() {

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
