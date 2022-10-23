package io.github.vantoozz.kli.runner.service

import java.util.concurrent.atomic.AtomicInteger

class InMemoryService : Service {

    private val calls = AtomicInteger(0)

    override fun call() {
        calls.incrementAndGet()
    }

    override fun callsMade(): Int = calls.get()
}
