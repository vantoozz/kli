import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.core.subcommands
import io.github.vantoozz.dikt.bind
import io.github.vantoozz.dikt.put
import io.github.vantoozz.kli.commands.BaseKliCommand
import io.github.vantoozz.kli.commands.LogEnvironment
import io.github.vantoozz.kli.kli
import io.github.vantoozz.kli.mocks.Logger
import io.github.vantoozz.kli.mocks.LoggerMock
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class CommandEnvironmentTest {

    @Test
//    @Ignore("Need to set up environment variable")
    fun `it handles environment variable`() {
        val mock = LoggerMock()
        val envvars = mapOf("KLI_ENVIRONMENT" to "some_value")

        BaseKliCommand {
            put(LogEnvironment.Environment(it ?: ""))
            bind<Logger>(mock)
        }.apply {
            context {
                envvarReader = { envvars[it] }
            }
            subcommands(LogEnvironment())
        }.parse(listOf("log-environment"))

        assertTrue {
            mock.logged("some_value")
        }
    }

    @Test
    fun `it handles environment long option`() {
        val mock = LoggerMock()

        kli(
            { environment ->
                put(LogEnvironment.Environment(environment ?: ""))
                bind<Logger>(mock)
            },
            LogEnvironment(),
        ).parse(listOf("--env=some_value", "log-environment"))

        assertTrue {
            mock.logged("some_value")
        }
    }

    @Test
    fun `it handles environment short option`() {
        val mock = LoggerMock()

        kli(
            { environment ->
                put(LogEnvironment.Environment(environment ?: ""))
                bind<Logger>(mock)
            },
            LogEnvironment(),
        ).parse(listOf("-e", "some_value", "log-environment"))

        assertTrue {
            mock.logged("some_value")
        }
    }
}
