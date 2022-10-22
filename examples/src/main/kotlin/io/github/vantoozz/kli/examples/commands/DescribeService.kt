package io.github.vantoozz.kli.examples.commands

import io.github.vantoozz.kli.commands.KliCommand
import io.github.vantoozz.kli.examples.Config
import org.slf4j.Logger
import org.slf4j.LoggerFactory

internal class DescribeService : KliCommand<DescribeService.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        handler.describe()
    }

    internal class Handler(
        private val config: Config,
    ) {

        private val logger: Logger by lazy {
            LoggerFactory.getLogger(javaClass)
        }

        fun describe() {
            logger.info(
                "Hostname: {}, port: {}",
                config.service.hostname,
                config.service.port
            )
        }
    }
}
