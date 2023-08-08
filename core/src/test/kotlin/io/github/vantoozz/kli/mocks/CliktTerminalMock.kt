package io.github.vantoozz.kli.mocks

import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.terminal.PrintRequest
import com.github.ajalt.mordant.terminal.TerminalInfo
import com.github.ajalt.mordant.terminal.TerminalInterface

internal class CliktTerminalMock : TerminalInterface {

    private val printed = mutableSetOf<String>()

    fun printed(text: String) = printed.contains(text)
    override val info: TerminalInfo
        get() = TerminalInfo(
            10,
            10,
            AnsiLevel.ANSI16,
            ansiHyperLinks = false,
            outputInteractive = false,
            inputInteractive = false,
            crClearsLine = false
        )

    override fun completePrintRequest(request: PrintRequest) {
        printed.add(request.text)
    }

    override fun readLineOrNull(hideInput: Boolean) = null


}
