package io.github.vantoozz.kli.mocks

import com.github.ajalt.clikt.output.CliktConsole

internal class CliktConsoleMock : CliktConsole {

    private val printed = mutableSetOf<String>()

    fun printed(text: String) = printed.contains(text)

    override fun print(text: String, error: Boolean) {
        printed.add(text.removeSuffix(lineSeparator))
    }

    override fun promptForLine(prompt: String, hideInput: Boolean) = null

    override val lineSeparator: String get() = System.lineSeparator()
}
