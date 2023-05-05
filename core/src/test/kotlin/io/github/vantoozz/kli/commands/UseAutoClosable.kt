package io.github.vantoozz.kli.commands

import io.github.vantoozz.kli.mocks.AutoClosableService

internal class UseAutoClosable : KliCommand<UseAutoClosable.Handler>() {

    override val handler = Handler::class

    override fun handle(handler: Handler) {
        handler.run()
    }

    internal class Handler(
        private val autoClosableService: AutoClosableService,
    ) {
        fun run() {
            autoClosableService.doSomething()
        }
    }
}
