package io.github.vantoozz.kli.mocks

internal class AutoClosableService(
    private val history: MutableList<String>,
) : AutoCloseable {

    fun doSomething() = Unit
    override fun close() {
        history.add("closed")
    }
}
