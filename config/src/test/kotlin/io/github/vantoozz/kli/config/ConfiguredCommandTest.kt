package io.github.vantoozz.kli.config

import io.github.vantoozz.dikt.put
import io.github.vantoozz.kli.config.commands.LogConfigType
import io.github.vantoozz.kli.config.commands.LogLoadedConfig
import io.github.vantoozz.kli.config.commands.LogStaticConfig
import io.github.vantoozz.kli.config.configs.LoadedConfig
import io.github.vantoozz.kli.config.configs.StaticConfig
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class ConfiguredCommandTest {

    @Test
    fun `it registers config`() {

        val logger = Logger()

        kli(
            { object : Konfig {} },
            { put(logger) },
            LogConfigType()
        ).parse(listOf("log-config-type"))

        assertTrue {
            logger.logged(Konfig::class.qualifiedName.toString())
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
            logger.logged(StaticConfig::class.qualifiedName.toString())
        }
    }

    @Test
    fun `it registers loaded config`() {

        val logger = Logger()

        kli<LoadedConfig>(
            { put(logger) },
            LogLoadedConfig()
        ).parse(listOf("log-loaded-config"))

        assertTrue {
            logger.logged("some_string_value")
        }
    }

    @Test
    fun `it registers loaded config for environment`() {

        val logger = Logger()

        kli<LoadedConfig>(
            { put(logger) },
            LogLoadedConfig()
        ).parse(listOf("-e", "env_1", "log-loaded-config"))

        assertTrue {
            logger.logged("env_1_string_value")
        }
    }
}
