package io.github.vantoozz.kli.runner

import io.github.vantoozz.dikt.put
import io.github.vantoozz.kli.config.KliConfig
import io.github.vantoozz.kli.runner.commands.One
import io.github.vantoozz.kli.runner.commands.Two
import io.github.vantoozz.kli.runner.service.InMemoryService
import io.github.vantoozz.kli.runner.service.Service
import kotlin.test.Test
import kotlin.test.assertSame

internal class RunnerTest {

    @Test
    fun `it runs command`() {

        val service = InMemoryService()

        kli<KliConfig>(
            {
                put<Service>(service)
            },
            arrayOf("one")
        ) {
            +One()
            +Two()
        }

        assertSame(5, service.callsMade())
    }
}
