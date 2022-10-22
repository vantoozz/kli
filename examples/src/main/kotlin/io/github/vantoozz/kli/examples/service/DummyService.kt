package io.github.vantoozz.kli.examples.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DummyService(
    private val hostname: String,
    private val port: Int,
) : Service {

    private val logger: Logger by lazy {
        LoggerFactory.getLogger(javaClass)
    }

    override fun doSomething() {
        logger.info("Connecting to {}:{}", hostname, port)
    }
}
