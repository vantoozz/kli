package io.github.vantoozz.kli.mocks

internal interface Logger {
    fun log(text: String)
}

internal class LoggerMock : Logger {
    private val logged = mutableSetOf<String>()

    fun logged(text: String) = logged.contains(text)

    override fun log(text: String) {
        logged.add(text)
    }
}
