package io.github.vantoozz.kli.config

import io.github.vantoozz.dikt.put
import io.github.vantoozz.kli.config.commands.LogConfigType
import io.github.vantoozz.kli.config.commands.LogLoadedConfig
import io.github.vantoozz.kli.config.commands.LogStaticConfig
import io.github.vantoozz.kli.config.configs.LoadedConfig
import io.github.vantoozz.kli.config.configs.StaticConfig
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class EmptyConfigTest {


    @Test
    fun `it loads empty config`() {

        val logger = Logger()

        kli<KliConfig>(
            { put(logger) },
            LogConfigType()
        ).parse(listOf("log-config-type"))

        assertTrue {
            logger.logged(KliConfig::class.qualifiedName.toString())
        }
    }
}
