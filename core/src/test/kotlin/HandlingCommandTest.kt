import com.github.ajalt.clikt.core.parse
import com.github.ajalt.clikt.core.subcommands
import io.github.vantoozz.dikt.bind
import io.github.vantoozz.kli.commands.LogSomething
import io.github.vantoozz.kli.commands.NotThrowingBaseKliCommand
import io.github.vantoozz.kli.kli
import io.github.vantoozz.kli.mocks.Logger
import io.github.vantoozz.kli.mocks.LoggerMock
import kotlin.test.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertTrue

internal class HandlingCommandTest {

    @Test
    fun `it creates handler`() {

        val mock = LoggerMock()

        kli(
            {
                bind<Logger>(mock)
            },
            LogSomething(),
        ).parse(listOf("log-something"))

        assertTrue {
            mock.logged("some string")
        }
    }

    @Test
    fun `it does nothing if no handler`() {
        assertDoesNotThrow {
            NotThrowingBaseKliCommand()
                .apply {
                    subcommands(LogSomething())
                }
                .parse(listOf("log-something"))
        }
    }
}
