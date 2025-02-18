package io.github.vantoozz.kli.examples.service

class DummyService(
    private val hostname: String,
    private val port: Int,
) : Service {
    override fun doSomething() {
        println("Connecting to $hostname:$port")
    }
}
