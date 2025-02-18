package io.github.vantoozz.kli.mocks

import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.terminal.PrintRequest
import com.github.ajalt.mordant.terminal.TerminalInfo
import com.github.ajalt.mordant.terminal.TerminalInterface

internal class CliktTerminalMock : TerminalInterface {

    private val printed = mutableSetOf<String>()

    fun printed(text: String) = printed.contains(text)
    override fun info(
        ansiLevel: AnsiLevel?,
        hyperlinks: Boolean?,
        outputInteractive: Boolean?,
        inputInteractive: Boolean?
    ) = TerminalInfo(
        ansiLevel = ansiLevel ?: AnsiLevel.ANSI16,
        ansiHyperLinks = hyperlinks == true,
        outputInteractive = outputInteractive == true,
        inputInteractive = inputInteractive == true,
        supportsAnsiCursor = false,
    )

    override fun completePrintRequest(request: PrintRequest) {
        printed.add(request.text)
    }

    override fun readLineOrNull(hideInput: Boolean) = null

}
