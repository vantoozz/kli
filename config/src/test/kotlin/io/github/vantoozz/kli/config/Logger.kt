package io.github.vantoozz.kli.config

internal class Logger {
    private val logged = mutableSetOf<String>()

    fun logged(text: String) = logged.contains(text)

    fun log(text: String) {
        logged.add(text)
    }
}
