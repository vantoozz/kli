package io.github.vantoozz.kli.config

import io.github.vantoozz.dikt.put
import io.github.vantoozz.kli.config.commands.LogConfigType
import io.github.vantoozz.kli.config.commands.LogStaticConfig
import io.github.vantoozz.kli.config.configs.StaticConfig
import io.github.vantoozz.kli.kli
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class ConfiguredCommandTest {

    @Test
    fun `it registers config`() {

        val logger = Logger()

        kli(
            { object : Config {} },
            { put(logger) },
            LogConfigType()
        ).parse(listOf("log-config-type"))

        assertTrue {
            logger.logged(Config::class.qualifiedName.toString())
        }
    }

    @Test
    fun `it registers static config`() {

        val logger = Logger()

        kli(
            { StaticConfig(it ?: "") },
            { put(logger) },
            LogStaticConfig()
        ).parse(listOf("log-static-config"))

        assertTrue {
            logger.logged(Config::class.qualifiedName.toString())
        }
    }
}
