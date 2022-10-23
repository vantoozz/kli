package io.github.vantoozz.kli.runner

import io.github.vantoozz.kli.runner.commands.One
import io.github.vantoozz.kli.runner.commands.Two
import kotlin.test.Test
import kotlin.test.assertSame

internal class CommandsListBuilderTest {

    @Test
    internal fun `it adds commands`() {
        val builder = CommandsListBuilder()
        builder.add(One())
        builder.add(Two())

        assertSame(2, builder.build().size)
    }

    @Test
    internal fun `it adds commands with unary plus`() {
        val builder = CommandsListBuilder()
            .apply {
                +One()
                +Two()
            }

        assertSame(2, builder.build().size)
    }
}
