package io.github.vantoozz.kli.examples.commands

import io.github.vantoozz.kli.commands.KliCommand
import io.github.vantoozz.kli.examples.Config

internal class DescribeService : KliCommand<DescribeService.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        handler.describe()
    }

    internal class Handler(
        private val config: Config,
    ) {

        fun describe() {
            println("Hostname: ${config.service.hostname}, port: ${config.service.port}")
        }
    }
}
